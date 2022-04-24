import pyrebase
from UltrasonicSesnorTest import distance
import time

config = {
  "apiKey": "AIzaSyC5jOsG3VGJfRmQyLfPA2KDL-Nr4xtT3vU",
  "authDomain": "floodthing-c588b.firebaseapp.com",
  "databaseURL": "https://floodthing-c588b-default-rtdb.firebaseio.com",
  "projectId": "floodthing-c588b",
  "storageBucket": "floodthing-c588b.appspot.com",
  "messagingSenderId": "376177091662",
  "appId": "1:376177091662:web:fba400fc24e4d6cd744f1f",
  "measurementId": "G-E39D1GDQRJ"
};

firebase = pyrebase.initialize_app(config)

storage = firebase.storage()
database = firebase.database()

if __name__ == '__main__':
    while True:
        a = distance()
        s=str(a)
        print ("Measured Distance = %.1f cm " % a)
        data = {"Distance": s}
        database.set(data)
        time.sleep(2)
        
