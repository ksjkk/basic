# 공통
전반적인 Feedback 방향성은 Backend의 초점이 높게 책정되어 있음
- - -
# FE
1. README 를 보고 UI에 굉장히 많이 신경을 써주신것 같은데 아쉽지만 UI 스타일은 검토 내용에 반영되지 않기 때문에 다른 부분에 시간을 투자하시는게 더 나았을 것
2. README 에 요구사항에 대한 디테일이 포함되어 가독성에 불편함이 있음, 핵심 기술과 전체적인 아키텍쳐의 구성을 나타내면 좋음
3. <span style='color:pink'>API 구현부</span>
```
api 구현부와 rendering 부분이 좀 더 명확하고 간결하게 연결되면 좋음
```
4. <span style='color:lightgreen'>구성</span>
```
business logic 을 포함한 rendering Layer, api 구현 Layer를
잘 나누어 관심사를 분리시켰음
```
5. <span style='color:red'>요구사항</span>
```
polling 방식으로 구현되지 않았음
setInterval(this.getList, 1000) 은 정말 10초마다 실행되는가
javascript의 thread 구성은 어떻게 되는가
javasciprt의 동작원리(event loop, message queue) 는 무엇인가
```
6. 종합
```
전반적 구성은 괜찮았지만 사용하지 않는 구성요소들이 남아있던점,
api설계와 구현이 조금더 간결하고 명확하게 연결되었으면 좋았을 것

store dispatch를 이용하는대신 api를 함수로 직접 구현한 것을
rendering Layer에서 import하여 사용하면 함수명으로 api의
용도를 명확하게 파악할 수 있고 더 간결한 코드가 될 것

제일 중요한 요구사항이 충족되지 못했음
설계가 부족해도 요구사항이 충족되었다면 더 높은 점수를 받았을 것
```
- - -
# BE
1. Intellij는 사용하지 않는지 궁금
2. Build Tool -> Gradle을 선호하긴 하지만 Maven도 상관없음
3. README 관련<br/>

- <span style='color:pink'>에러코드와 메세지</span>
```
API를 받는쪽에서는 에러코드가 많으면 불편
에러코드는 통일하고 메세지에 사유를 담아 front에서 에러코드면 메세지를
보여주게끔 처리하였다면 좋았을 것
```
- id type은 UUID보다 Long 지향
- spring security 의 protected configure의 preflight 통해 cors 들을 간결하게 만들 수 있을 것.
- Controller Advice에 최상위 Exception이 Custom Exception에 Runtime Exception으로 잡혀있는데 IOException 등은 Runtime Exception에 해당하지 않기 때문에 최상위는 Exception 으로 잡는게 안전 (요구사항에는 IO가 없기 때문에 Runtime으로 충분히 커버가능)
- Security -> csrf disabled 이후 왜 다시 추가했는지
4. <span style='color:lightgreen'>테스트</span>
```
몇개 빠진게 있긴 하지만 given when then 구현해주셔서 가독하기 좋았음
```
5. Layer 정리
```
...com/pay 하위 package 너무 많음
아키텍쳐의 설계가 잘못됐을 가능성이 큼, Layer의 분리가 필요함
```
6. <span style='color:pink'>DI 방식</span>
```
Autowired Annotation 보다 생성자 주입방식을 지향함
```
7. <span style='color:red'>controller validation</span>
```
request parameter의 validation 취약
request body 의 parameter dto validation 취약
```
8. <span style='color:pink'>application 설계</span>
```
현재 데이터 조회시
Controller -> Service(Itf) -> Impl -> Repo(Itf) -> xml

JPA를 활용하면 간결해질수 있음
Controller -> Service -> Repo(Itf)

JPA + MyBatis
JPA + QueryDSL 등을 지향하고 MyBatis 단독 사용은 지양함

JPA를 사용하여 Entity와 DTO의 관계를 명확히 하는것을 지향

Controller 에서 Parameter의 Validation을 진행하고
Service에서 Business Logic에 집중하는 것이 좋음
```
9. <span style='color:red'>요구사항</span>
```
동시성 문제를 고려한 부분을 찾지 못했음
```
- - -
#정리
FE에서는 템플릿 설계는 괜찮았다고 생각합니다. 렌더링, API구현 Layer를 나누어서 ocp도 어느정도 지켜진 것 같구요.
<br/>다만 요구되는 핵심기능이 구현되지 못했습니다. 핵심기능은 단순 동작을 넘어서 javascript의 동작원리를 파악하는 중요한 지표였습니다.

BE에서는 여러 피드백이 나왔는데요, 구현하신 방식 자체에 의문점들이 있었습니다. 예로들어 DI를 구현하는데 생성자 주입방식이 각광받고 널리 쓰이게 된 이유를 이해해보시면 될 것같습니다.
<br/>JPA를 사용하지 않은 이유도 궁금한데요, JPA를 활용하시면 생산성이 굉장히 올라갑니다.
<br/>추가로 요구사항중 동시성 문제가 고려되지 않았습니다. 이부분은 저희 도메인 특성상 크게 고려할 문제는 아니라서 큰 요인은 아니지만 과제의 중요 요구사항이었기 때문에 생각해 볼 문제였다고 생각합니다.

정리하자면, FE에서는 javascript의 동작원리를 이해하고 있는지 파악할 수 없었고, BE에서는 전반적인 기술스택이 맞지 않았습니다.
</br>Spring Security, JPA, Application 과 Entity의 설계가 조금 아쉽지 않았나 싶습니다.