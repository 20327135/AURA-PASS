from motor.motor_asyncio import AsyncIOMotorClient


MONGO_DETAILS = "mongodb+srv://begoodandshine365_db_user:pWmG0CubsMvOCdKJ@cluster0.bcgk3v7.mongodb.net/?retryWrites=true&w=majority&appName=aura-pass"


client = AsyncIOMotorClient(
    MONGO_DETAILS,
    tls=True,
    tlsAllowInvalidCertificates=True
)

# 사용할 데이터베이스 이름
database = client.aura_pass