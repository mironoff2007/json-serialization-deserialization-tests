# json-serialization-deserialization-tests

Comparison of json deserialization:
  <br />-gson
  <br />-gson without annotations
  <br />-jackson 
  <br />-kotlinx
  <br />-moshi (see code https://github.com/mironoff2007/moshi-deserialization-test)
  
kotlin version for moshi - 1.3.72*
kotlin version for other - 1.6.21

  Deserialization of list with 5000 objects-tree of 20 classes. Each object has 5 different fields (string,  double,  boolean, long, int) and field of class with same type and one field which is allways null.
  This object was generated with auto incremented fileds value and converted to json string. 
  
  Each test was repeted 10 times. 
  Time of initialization of deserilizer for each library is not included in test. 
  Run time in ms,  lower is better. 
![image](https://user-images.githubusercontent.com/18057056/188313381-e142388e-f03c-4657-bbb9-6d0327b97b91.png)

![image](https://user-images.githubusercontent.com/18057056/188313395-22cbdfca-27df-49a2-adde-9038e6259399.png)

![image](https://user-images.githubusercontent.com/18057056/188313406-da14eb1b-0f20-4ae3-8140-2e7070420c89.png)

To reproduce find in JsonDesTest.kt in test folder and run. Moshi test is located in deparate repo,  find. 
JsonDesTest.kt in moshi repo link is located above. 

* big disadvantage of moshi is conflicts with kotlin version. I could not build moshi test with kotlin versions 1.5-1.7.  
Maybe there is solution to build it,  but it is painfull to find it.  I love "out of the box" solutions.
 
