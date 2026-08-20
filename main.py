from datetime import datetime, timedelta
import jwt
from fastapi import FastAPI, HTTPException, status, Header, Depends, Query
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
from passlib.context import CryptContext

# 본인이 만드신 database.py 연동
from database import database, client 

# 앱 초기화
app = FastAPI(title="AURA-PASS API", version="1.0")

# CORS 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# 설정
pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")
SECRET_KEY = "aurapass_super_secret_key_change_later"
ALGORITHM = "HS256"
ACCESS_TOKEN_EXPIRE_MINUTES = 60 * 24

# MongoDB 컬렉션 지정
users_collection = database["users"]
products_collection = database["products"]
fitting_sessions_collection = database["fitting_sessions"]
saved_fittings_collection = database["saved_fittings"]
looks_collection = database["looks"]
saved_looks_collection = database["saved_looks"]
lounge_menu_collection = database["lounge_menu"]
menu_feedback_collection = database["menu_feedback"]
wishlist_collection = database["wishlist"]
next_visit_collection = database["next_visit"]
error_logs_collection = database["error_logs"]          # 시스템 장애 로그 컬렉션
privacy_consent_collection = database["privacy_consent"]  # 개인정보 및 동의 관리 컬렉션

# --- 이벤트 핸들러 ---
@app.on_event("startup")
async def startup_event():
    try:
        await client.admin.command('ping')
        print("✅ MongoDB 연결 성공! (AURA-PASS DB Ready)")
    except Exception as e:
        print(f"❌ MongoDB 연결 실패: {e}")

@app.on_event("shutdown")
async def shutdown_event():
    client.close()
    print("🛑 MongoDB 연결이 안전하게 종료되었습니다.")

# --- 데이터 모델 ---
class UserCreate(BaseModel): email: str; password: str
class UserLogin(BaseModel): email: str; password: str
class QRScanRequest(BaseModel): qr_data: str
class FittingStartRequest(BaseModel): fitting_room_id: str
class FittingItemAddRequest(BaseModel): rfid_tag: str; name: str; color: str
class FittingSaveRequest(BaseModel): saved_items: list
class LookSaveRequest(BaseModel): look_id: str; look_name: str; items: list
class FeedbackRequest(BaseModel): menu_id: str; rating: int; comment: str
class WishlistRequest(BaseModel): product_id: str; name: str; color: str; price: int
class NextVisitRequest(BaseModel): visit_date: str; memo: str; items: list
class ErrorReportRequest(BaseModel): device_id: str; error_type: str; description: str
class ConsentRequest(BaseModel): marketing_consent: bool; biometric_consent: bool; location_consent: bool

# --- 인증 함수 ---
async def get_current_user(authorization: str = Header(...)):
    try:
        token = authorization.split(" ")[1]
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM])
        email = payload.get("sub")
        if not email: raise HTTPException(status_code=401, detail="인증 실패")
        return email
    except: raise HTTPException(status_code=401, detail="로그인이 필요합니다.")

# --- API 엔드포인트 ---

@app.get("/")
async def root():
    return {"message": "AURA-PASS 백엔드 서버가 정상적으로 작동 중입니다!"}

# 1. 회원가입 API
@app.post("/api/signup")
async def signup(user: UserCreate):
    if await users_collection.find_one({"email": user.email}):
        raise HTTPException(status_code=400, detail="이미 가입된 이메일입니다.")
    hashed = pwd_context.hash(user.password)
    await users_collection.insert_one({"email": user.email, "password": hashed})
    return {"success": True, "message": "가입 완료"}

# 2. 로그인 API
@app.post("/api/login")
async def login(user: UserLogin):
    db_user = await users_collection.find_one({"email": user.email})
    if not db_user or not pwd_context.verify(user.password, db_user["password"]):
        raise HTTPException(status_code=400, detail="이메일 또는 비밀번호 불일치")
    expire = datetime.utcnow() + timedelta(minutes=ACCESS_TOKEN_EXPIRE_MINUTES)
    token = jwt.encode({"sub": user.email, "exp": expire}, SECRET_KEY, algorithm=ALGORITHM)
    return {"success": True, "access_token": token, "token_type": "bearer"}

# 3. 메인 데이터 조회 API
@app.get("/api/main")
async def get_main_data(current_user_email: str = Depends(get_current_user)):
    return {
        "success": True,
        "message": "메인 데이터 조회 성공",
        "customer": {"email": current_user_email, "grade": "VIP", "preferred_style": ["Minimal", "Casual"]},
        "fitting_room": {"status": "active", "current_items": [{"name": "오버핏 코트", "rfid": "RFID-88392"}]},
        "ai_recommendations": {"coordination": "캐시미어 니트", "welcome_drink": "허브티"}
    }

# 4. QR 코드 인식 API
@app.post("/api/qr-login")
async def qr_login(request: QRScanRequest):
    try:
        payload = jwt.decode(request.qr_data, SECRET_KEY, algorithms=[ALGORITHM])
        email = payload.get("sub")
        if not email: raise HTTPException(status_code=400, detail="유효하지 않은 QR입니다.")
        return {"success": True, "message": "QR 인식 성공", "customer": {"email": email, "grade": "VIP"}}
    except:
        raise HTTPException(status_code=400, detail="잘못된 QR입니다.")

# ==========================================
# 5. 스마트 피팅룸 & 스타일링 & 라운지 & 룩북 API (기존 유지)
# ==========================================

@app.get("/api/lookbook/my-looks")
async def get_my_looks(current_user_email: str = Depends(get_current_user)):
    return {"success": True, "my_looks": [{"look_id": "LOOK-001", "look_name": "모던 클래식 웜톤 코디"}]}

@app.get("/api/lookbook/wishlist")
async def get_wishlist(current_user_email: str = Depends(get_current_user)):
    return {"success": True, "wishlist": [{"product_id": "P001", "name": "울 블렌디드 오버핏 코트"}]}

@app.post("/api/lookbook/next-visit")
async def save_next_visit(request: NextVisitRequest, current_user_email: str = Depends(get_current_user)):
    return {"success": True, "message": f"{request.visit_date} 방문 예약 및 피팅룸 사전 준비 완료"}

# ==========================================
# 7. 시스템 장애 대응 & 개인정보 동의 관리 API (신규 추가)
# ==========================================

# (1) 시스템 상태 및 헬스체크 API (장애 대응 모니터링)
@app.get("/api/system/health")
async def system_health_check():
    try:
        await client.admin.command('ping')
        db_status = "healthy"
    except Exception:
        db_status = "unhealthy"

    return {
        "success": True,
        "server_status": "running",
        "database_status": db_status,
        "checked_at": datetime.utcnow()
    }

# (2) 기기 장애/에러 신고 API (미러, RFID 리더기, 키오스크 오류 수집)
@app.post("/api/system/report-error")
async def report_system_error(request: ErrorReportRequest):
    error_log = {
        "device_id": request.device_id,
        "error_type": request.error_type,       # 예: "RFID_DISCONNECT", "MIRROR_TOUCH_FAIL"
        "description": request.description,
        "reported_at": datetime.utcnow()
    }
    await error_logs_collection.insert_one(error_log)
    return {
        "success": True,
        "message": "장애 신고가 접수되었습니다. 관리자에게 알림이 전송됩니다.",
        "error_id": request.error_type
    }

# (3) 개인정보 및 바이오/AI 인식 동의 조회 API
@app.get("/api/privacy/consent")
async def get_privacy_consent(current_user_email: str = Depends(get_current_user)):
    consent = await privacy_consent_collection.find_one({"email": current_user_email})
    if not consent:
        consent = {"marketing_consent": False, "biometric_consent": False, "location_consent": False}
    else:
        consent["_id"] = str(consent["_id"])
    return {"success": True, "consent_settings": consent}

# (4) 개인정보 및 바이오 인식 동의 설정/변경 API
@app.post("/api/privacy/consent")
async def update_privacy_consent(request: ConsentRequest, current_user_email: str = Depends(get_current_user)):
    consent_data = {
        "email": current_user_email,
        "marketing_consent": request.marketing_consent,
        "biometric_consent": request.biometric_consent,  # 스마트 미러 AI 체형/컬러 분석용 동의
        "location_consent": request.location_consent,      # 매장 내 NFC/RFID 자동 감지용 동의
        "updated_at": datetime.utcnow()
    }
    await privacy_consent_collection.update_one(
        {"email": current_user_email},
        {"$set": consent_data},
        upsert=True
    )
    return {
        "success": True,
        "message": "개인정보 및 활용 동의 내역이 안전하게 반영되었습니다.",
        "consent_settings": consent_data
    }