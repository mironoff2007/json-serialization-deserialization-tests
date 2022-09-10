# json-serialization-deserialization-tests

Comparison of json deserialization:
  <br />-gson
  <br />-gson without annotations
  <br />-jackson 
  <br />-kotlinx
  <br />-moshi 
  

  Deserialization of list with 5000 objects-tree of 20 classes. Each object has 5 different fields (string,  double,  boolean, long, int) and field of class with same type and one field which is allways null.
  This object was generated with auto incremented fileds value and converted to json string. 
  
  Each test was repeted 10 times. 
  Time of initialization of deserilizer for each library is not included in test. 
  Run time in ms,  lower is better. 
![image](https://user-images.githubusercontent.com/18057056/188313381-e142388e-f03c-4657-bbb9-6d0327b97b91.png)

![image](https://user-images.githubusercontent.com/18057056/188313395-22cbdfca-27df-49a2-adde-9038e6259399.png)

![image](https://user-images.githubusercontent.com/18057056/188313406-da14eb1b-0f20-4ae3-8140-2e7070420c89.png)




