from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
from contextlib import asynccontextmanager


from database import database, client 


@asynccontextmanager
async def lifespan(app: FastAPI):
    try:
        await client.admin.command('ping')
        print("✅ MongoDB 연결 성공! (AURA-PASS DB Ready)")
    except Exception as e:
        print(f"❌ MongoDB 연결 실패: {e}")
    
    yield 
    
    client.close()
    print("🛑 MongoDB 연결이 안전하게 종료되었습니다.")

# 앱 초기화 
app = FastAPI(title="AURA-PASS API", version="1.0", lifespan=lifespan)

# CORS 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/")
async def root():
    return {"message": "AURA-PASS 백엔드 서버가 정상적으로 작동 중입니다!"}

