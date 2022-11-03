<h2>파일 확장자 차단 (Spring boot + Ajax)</h2>
<h4>스프링부트를 이용해 파일 확장자를 차단하는 페이지를 구현했습니다.</h4>
<h5>링크에서 확인 가능합니다-> <a href ="http://52.7.156.49/">http://52.7.156.49/</a></h5>
<hr>

<h3>Dependency</h3>
<li>Spring boot</li>
<li>Thymeleaf</li>
<li>MySQL</li>
<li>Lombok</li>
<li>Spring web</li>
<li>JPA</li>
<li>AWS(EC2, RDS)</li><br>

<h3>개발환경</h3>
<li>스프링부트버전 : 2.7.5</li>
<li>JDK 버전 : jdk1.8.0_321 (JAVA 1.8)</li>
<li>빌드 툴 : Gradle</li><br>


<h3>기본요건</h3>
<li>고정확장자는 차단을 자주하는 확장자리스트이며, default 는 unCheck</li>
<li>고정확장자를 check or uncheck 할 경우 db에 저장됨 -새로고침시 유지되어야 함</li>
<li>확장자 최대 입력 길이는 20자리</li>
<li>추가 버튼 클릭 시 db저장되며, 아래쪽 영역에 표시됨</li>
<li>커스텀 확장자는 최대 200개까지 추가 가능</li>
<li>확장자 옆 X 클릭 시 db에서 삭제</li>
<br>
<h3>추가요건</h3>
<li>확장자는 영문 + 숫자만 입력가능하도록 정규표현식으로 제한 (한글, 특수문자, 공백 불가)</li>
<li>고정확장자인 경우 커스텀확장자로 등록 불가</li>
<li>등록된 커스텀 확장자는 중복등록 불가</li>
<li>고정확장자 check + 커스텀확장자 추가 + 등록불가문구출력 = Ajax 비동기 통신사용</li>
<li>클라이언트에서 요청된 확장자에 대해서만 화면랜더링</li><br>
<p><b>ex )</b> 커스텀확장자를 추가하면 서버에서 업데이트된 커스텀확장자 리스트만 응답되어 화면이 랜더링 됨 ▼ </p>

![랜더링](https://user-images.githubusercontent.com/105329813/199792718-71eeb627-6242-435b-b118-29d1b846d5e7.png)




