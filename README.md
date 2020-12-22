# HackathonApplication

2020년도 공공데이터 청년인턴십 언택트 해커톤

---

- 일정 : 11월 23일 ~ 12월 21일
- 수상결과 : 우수상 (한국정보화진흥원장상)
- 공모부문 : 웹/앱 서비스 개발 부문 -> 앱 서비스 개발

---

## 앱 제안 배경과 필요성

> 시니어를 위한 종합 포털사이트 (건강, 교육, 앱, 커뮤니티 서비스)

- 컨셉 : 공공데이터를 활용하여 시니어에게 유용한 앱서비스를 제공하자

- 제안 배경 : 시니어를 힘들게하는 4고(苦) - 신체적 질병, 사회적 고립, 경제적 빈곤, 정신적 고독을 해결하기 위한 4가지 서비스 제공


<br/>

- 페르소나를 통한 사용자의 Pain Point 도출

![페르소나](https://user-images.githubusercontent.com/36183001/102783230-f6a87280-43dd-11eb-94d8-25d990696787.PNG)

> 시니어의 4가지 Pain Point 도출 과정

---

- 유사서비스 분석과 FGI(Focus Group Interview) 를 통해 니즈 도출

![유사서비스](https://user-images.githubusercontent.com/36183001/102785201-e8a82100-43e0-11eb-8c0b-ffaee49b17bd.png)

> 개인 맞춤형 서비스를 원하는 시니어의 니즈 도출 -> 머신러닝 기술로 니즈 충족

---

- 어플리케이션의 메뉴구조도

![메뉴구조도](https://user-images.githubusercontent.com/36183001/102783237-f7d99f80-43dd-11eb-9801-111fc7a7f69e.PNG)




## 실제 구동 화면

1. 메인화면

![main](https://user-images.githubusercontent.com/36183001/102785507-74ba4880-43e1-11eb-9327-fc54d0701ccd.png)

> 4가지 기능별 트로피와 이수증 확인 가능


<br/>

2. 건강 메인 / 운동 측정 / 운동 측정 결과 / 추천 운동 강좌

![health](https://user-images.githubusercontent.com/36183001/102785628-9fa49c80-43e1-11eb-8dce-07f05c6df377.png)

> 신체나이와 추천 운동리스트 확인 가능 / 집에서 간단히 할 수 있는 3가지 운동 측정 / 측정 결과에 따른 점수 부여 / 국민체육진흥공단에서 제공하는 운동 정보 제공


<br/>

3. 교육 메인 / 교육 필터 / 리스트 클릭 시 교육 화면이동

![edu](https://user-images.githubusercontent.com/36183001/102785500-72f08500-43e1-11eb-965b-ae737fe7a18f.png)

> 추천 시스템을 통해 추천된 교육 강좌 / 필터기능을 통해 정렬 가능 / 해당 링크로 이동


<br/>

4. 일자리 메인 / 일자리 필터 / 리스트 클릭 시 일자리 화면이동

![job](https://user-images.githubusercontent.com/36183001/102785503-7421b200-43e1-11eb-8bca-7ac8d9b2de26.png)

> 추천 시스템을 통해 추천된 일자리 정보 / 필터기능을 통해 정렬 가능 / 해당 링크로 이동


<br/>

5. 커뮤니티 메인 / 커뮤니티 카테고리 / 글쓰기 탭 / 내가 쓴 글 보기

![comm](https://user-images.githubusercontent.com/36183001/102785505-74ba4880-43e1-11eb-991d-43a24b418e87.png)

> 카테고리 관계없이 모든 글 표시 / 시니어 관심사에 따른 카테고리 표시 / 카테고리에 따른 글쓰기 / 내가 쓴 글 보기




## 활용된 머신러닝 기술

1. 운동 추천 머신러닝

![건강 모델링](https://user-images.githubusercontent.com/36183001/102784352-9e727000-43df-11eb-8aa8-7999d8b27df1.PNG)

> 집에서 간단하게 측정가능한 3가지 운동 정보를 통해 사용자의 신체능력을 향상시킬 수 있는 운동 리스트를 추천해줌


<br/>

2. 일자리/교육 카테고리 표준화 / t-SNE를 사용한 군집별 시각화

![표준화](https://user-images.githubusercontent.com/36183001/102784353-9f0b0680-43df-11eb-84a3-f488990d4e2d.PNG)

![표준화 결과](https://user-images.githubusercontent.com/36183001/102784354-9fa39d00-43df-11eb-8e67-e67bc6e341ba.PNG)

> 카테고리화 되어 있지 않은 일자리/교육 데이터를 표준화하여 사용자가 원하는 정보를 쉽게 찾을 수 있도록 데이터 정제 / 10개 라벨링으로 군집화 된 결과를 보여줌


<br/>

3. 추천 시스템

![추천 시스템](https://user-images.githubusercontent.com/36183001/102784355-a03c3380-43df-11eb-97f3-f109249ae280.PNG)

> 일자리/교육 카테고리 표준화를 통해 나뉘어진 카테고리를 데이터 베이스화 하여 각 카테고리별 가중치를 설정함(활동, 관심사) -> 가중치에 따른 코사인 유사도를 계산하여 사용자가 흥미있어하는 종목을 추천해줌




## 기대효과

![기대효과](https://user-images.githubusercontent.com/36183001/102784188-5eab8880-43df-11eb-9a0f-1d49e41a1c8e.PNG)

> 4가지 기능을 (건강, 교육, 일자리, 커뮤니티) 통해서 얻을 수 있는 기대효과


## 사용된 기술스택

- 앱 개발 : Java (Android Studio)

- 머신러닝 : Python (FastText, RandomForest)


## 활용한 공공데이터

- 국민체육진흥공단, '체력측정 항목별 측정 데이터'

- 국민체육진흥공단, '체력측정 운동처방 데이터'

- 서울 열린데이터 광장, '서울시 50플러스 포털 교육정보'

- 서울 열린데이터 광장, '서울시일자리센터 교육정보'

- 서울 열린데이터 광장, '전국평생학습강좌표준데이터'

- 공공데이터 포털, '한국노인인력개발원 - 노인 구인정보'

- 공공데이터 포털, '한국노인인력개발원  - 자립형 일자리 수행기관'

- 공공데이터 포털, '한국노인인력개발원  - 노인사회활동 시스템 코드 정보'

- 공공데이터 포털, '한국노인인력개발원  - 노인 재능나늠활동 모집공고 수행기관 정보'


## 앱 디자인 템플릿

> 제플린 : https://app.zeplin.io/project/5fbfb95d03ee5f0159e46856


## 앱 초기 프로토타입

> Invision Studio 프로토 타입 링크 : https://youngbaekim24850.invisionapp.com/prototype/portal-cki793bcq001aqx01jdslrul5


## 2020년도 공공데이터 청년인턴십 언택트 해커톤 

![공고](https://user-images.githubusercontent.com/36183001/102781915-c8c22e80-43db-11eb-89ad-6fbaf2935dce.PNG)
