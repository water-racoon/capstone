import cv2
import tensorflow.keras
import numpy as np
from gpiozero import LED, Motor, Servo
import time
#이미지 전처리
def preprocessing(frame):
    #사이즈 조정
    size=(224,224)
    frmae_resized = cv2.resize(frame, size, interpolation=cv2.INTER_AREA)

    #이미지 정규화
    frame_normalized = (frame_resized.astype(np.float32) / 127.0) -1

    #예측을 위해 reshape해줌
    frame_reshaped = frame_normalized.frame_reshaped((1,224,224,3))

    return frame_reshaped
#모터의 회전설정, 설정맞추기
motor = Motor(forword=20, backword=20)
#분류값에 맞춰서 모터를 필요각도까지 속도조절로 맞춘다.
def roll(range):
    if range==0:
        motor.forword(speed=0)
    elif range==1:
        motor.forword(speed=0.25)
    elif range==2:
        motor.forword(speed=0.5)
    elif range==3:
        mother.forword(speed=0.75)
    time.sleep(2)

#분류값에 맞춰서 모터를 필요각도까지 속도조절로 맞춘다.
def backroll(range):
    if range==0:
        motor.backword(speed=0)
    elif range==1:
        motor.backword(speed=0.25)
    elif range==2:
        motor.backword(speed=0.5)
    elif range==3:
        mother.backword(speed=0.75)
    time.sleep(2)

#서보모터의 회전설정, 버리는 반때기 각도설정
servo = Servo(26)

# 러닝머신 불러오기
machine_filename=''
machine=tensorflow.keras.models.load_model(machine_filename)

#카메라 캡쳐 객체, 0=내장 카메라
capture = cv2.VideoCapture(0)

#캡쳐 프레임 사이즈 조절
cpature.set(cv2.CAP_PROP_FRAME_WIDTH, 320)
cpature.set(cv2.CAP_PROP_FRAME_HEIGHT, 240)

object_check = 0
while True: #무한반복
    #한 프레임씩 읽기
    ret, frame= capture.read()

    #이미지 뒤집기, 1 = 좌우 반전
    frame_filped = cv2.flip(frame,1)

    #데이터 전처리
    preprocessed = preprocessing(frame_filped)

    #에측
    prediction = machine.predict(preprocessed)

    value = prediction.argmax()
    roll(value)
    servo.mid()
    time.sleep(1)
    serbo.max()
    backroll(value)
    continue

capture.release()
ddd
