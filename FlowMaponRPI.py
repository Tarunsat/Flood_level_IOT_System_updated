#Libraries
import Adafruit_DHT as dht
from time import sleep
import csv
import time
import pyrebase
import RPi.GPIO as GPIO
from collections.abc import Mapping, MutableMapping
#Set DATA pin
DHT = 4

config = {
  "apiKey": "AIzaSyC5jOsG3VGJfRmQyLfPA2KDL-Nr4xtT3vU",
  "authDomain": "floodthing-c588b.firebaseapp.com",
  "databaseURL": "https://floodthing-c588b-default-rtdb.firebaseio.com/",
  "projectId": "floodthing-c588b",
  "storageBucket": "floodthing-c588b.appspot.com",
  "messagingSenderId": "376177091662",
  "appId": "1:376177091662:web:fba400fc24e4d6cd744f1f",
  "measurementId": "G-E39D1GDQRJ"
};

firebase = pyrebase.initialize_app(config)
storage = firebase.storage()
database = firebase.database()
db =database.child("Nodes").child("Node1")
X = "12.97086754548284"
Y = "79.15951695689358"

GPIO.setmode(GPIO.BCM)


GPIO_TRIGGER = 20
GPIO_ECHO = 21

GPIO.setup(GPIO_TRIGGER, GPIO.OUT) 
GPIO.setup(GPIO_ECHO, GPIO.IN)

def distance(): 
    GPIO.output(GPIO_TRIGGER, True) 
    time.sleep(0.00001) 
    GPIO.output(GPIO_TRIGGER, False)


    StartTime = time.time() 
    StopTime = time.time()

    while GPIO.input(GPIO_ECHO) == 0: 
        StartTime = time.time()

    while GPIO.input(GPIO_ECHO) == 1: 
        StopTime = time.time()

    TimeElapsed = StopTime - StartTime 
    distance = (TimeElapsed * 34300) / 2 
    return distance

while True:
    #Read Temp and Hum from DHT22
    h,t = dht.read_retry(dht.DHT22, DHT)
    #Print Temperature and Humidity on Shell window
    a = distance()
    dist=str(a)
    temp=str(t)
    humi=str(h)
    print('Temp={0:0.1f}*C  Humidity={1:0.1f}% ' .format(t,h),end=" ")
    print('Distance:'+dist)
    data = {
                "Distance" : dist,
                "X" : X,
                "Y" : Y,
                "humidity" : humi,
                "temperature" : temp
            }
    db.update(data)
    sleep(1) #Wait 5 seconds and read again
