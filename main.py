from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel

from database import database, client 

# 앱 초기화 (lifespan 제거)
app = FastAPI(title="AURA-PASS API", version="1.0")

# CORS 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# 앱 시작 시 실행 (Python 3.6 호환 방식)
@app.on_event("startup")
async def startup_event():
    try:
        await client.admin.command('ping')
        print("✅ MongoDB 연결 성공! (AURA-PASS DB Ready)")
    except Exception as e:
        print(f"❌ MongoDB 연결 실패: {e}")

# 앱 종료 시 실행 (Python 3.6 호환 방식)
@app.on_event("shutdown")
async def shutdown_event():
    client.close()
    print("🛑 MongoDB 연결이 안전하게 종료되었습니다.")

@app.get("/")
async def root():
    return {"message": "AURA-PASS 백엔드 서버가 정상적으로 작동 중입니다!"}
