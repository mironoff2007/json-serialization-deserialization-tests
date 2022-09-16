# json-serialization-deserialization-tests

Comparison of json deserialization:
  <br />-gson
  <br />-gson without annotations
  <br />-jackson 
  <br />-kotlinx
  <br />-moshi 
  
  1) Deserialization of list with 50 objects-tree of 200 inner classes.
  2) Deserialization of big geo json with coorinates of polylines.
  Json string for 2 cases is located in .json file approx. 1.3 Mb in raw resources.

  Test devices
  1) Asus ZC500TG (Asus)
  2) Realme c25s (Realm)
  3) Emulator Pixel 4 api 29 (emu) (notebook i7-4600u, 16 gb ram)

Nested object test 
![image](https://user-images.githubusercontent.com/18057056/190706053-388c0293-2df9-4d8c-9cd7-52d3769b72e7.png)

![image](https://user-images.githubusercontent.com/18057056/190706116-abafa661-b02a-43cd-b06c-3f0e8d6d5052.png)

![image](https://user-images.githubusercontent.com/18057056/190706158-ca5a8bd9-18ea-4c5b-8771-cbd4b0bc6b3e.png)

GeoJson test

![image](https://user-images.githubusercontent.com/18057056/190706238-321d6ab5-16ed-4a88-923f-084ead0e8e67.png)

![image](https://user-images.githubusercontent.com/18057056/190706308-e1d72940-090e-4227-902c-1fe78bd9a059.png)

![image](https://user-images.githubusercontent.com/18057056/190706373-a4b29ec4-a593-46da-b399-b5bb5f09e3be.png)
