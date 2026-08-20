# database.py 파일 내용을 아래와 같이 잠시 수정해보세요.
import motor.motor_asyncio

# MONGO_DETAILS = os.getenv("MONGO_DETAILS", "mongodb://localhost:27017") <-- 이 줄을 지우고
MONGO_DETAILS = "mongodb+srv://begoodandshine365_db_user:pWmG0CubsMvOCdKJ@cluster0.bcgk3v7.mongodb.net/?appName=aura-pass"

client = motor.motor_asyncio.AsyncIOMotorClient(MONGO_DETAILS)
database = client.aura_pass