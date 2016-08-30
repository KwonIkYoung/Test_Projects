# Test_Projects
각종 테스트를 위한 프로젝트

현재까지 구현되어 있는 테스트 1. 특정 Package 가 설치되어 있는지 여부 판단 2. 문자 자르기 3. Video 테스트 ( VerticalViewPager - HorizontalViewPager 안에 적용 )

1. UniversalVideoView
2. VideoViewClass ( 라이브러리 )
3. Base64 Encode
4. 정규식 테스트 
    String period = ""; 
    boolean flag = TextUtils.isEmpty(period) == false && Pattern.matches("^[0-9]*$", period) ; 
    Toast.makeText(TestMainActivity.this, "flag : "+ flag, Toast.LENGTH_SHORT).show();
5. reCatcha
  - 구글 reCatcha ( 라이브러리 사용 )
6. ExoPlayer ( 3번과 같은 방식 )
7. ExoPlayer-Texture 라이브러리 ( 3번과 같음 )
8. 접근성 테스트 (미완)
  - 접근성 체크 받은 후 알림 받아오기
