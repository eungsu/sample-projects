# 다음 우편번호 검색

## 적용방법
1. 입력폼에 우편번호, 기본주소, 상세주소 입력필드를 정의한다.
```html
<!--
  우편번호, 기본주소 입력필드는 읽기전용으로 설정한다.
  우편번호 검색버튼은 type="button"으로 타입을 지정한다.
  우편번호 검색버튼에 id="btn-search-postcode" 속성을 추가한다.
-->
<form>
  <div class="mb-2">
    <label class="form-label">우편번호</label>
    <div class="d-flex justify-content-start">
      <input type="text" class="form-control w-25 me-3" name="postcode" readonly="readonly"/>
      <button type="button" class="btn btn-dark btn-sm" id="btn-search-postcode">우편번호검색</button>
    </div>
  </div>
  <div class="mb-2">
    <label class="form-label">기본주소</label>
    <input type="text" class="form-control" name="address1" readonly="readonly"/>
  </div>
  <div class="mb-2">
    <label class="form-label">상세주소</label>
    <input type="text" class="form-control" name="address2" />
  </div>
</form>
```

2. 다음 우편번호 검색 스크립트를 추가한다.
```html
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
```

3. 우편번호 검색버튼을 클릭했을 때 실행되는 이벤트 핸들러 함수를 등록한다.
```javascript
/*
	// 우편번호검색 버튼을 클릭했을 때 실행할 이벤트 핸들러 함수를 등록한다.
	$("#btn-search-postcode").click(function() { ... })
	
	// 우편번호 검색 팝업을 화면에 표시한다.
	new daum.Postcode().open();
	
	// 우편번호 검색 팝업에서 검색된 주소를 클릭했을 때 실행되는 콜백함수를 구현한다.
	oncomplete: function(data) { ... }
	
	// data는 사용자가 우편번호 검색창에서 선택한 주소정보가 들어있는 자바스크립트 객체다.
	data = {
		// 도로명 주소를 선택했을 때  'R'이다. 
		userSelecteType: 'R',
		// 공동주택일 경우 'Y'다.
		apartment: 'N', 
		// 건물명이 있는 경우 표시된다.
		buildingName: '에이치퀘어 엔동',
		// 우편번호다.
		zonecode: '13494',
		// 도로명주소다.
		roadAddress: '경기 성남시 분당구 판교역로 235',
		// 지번주소다.
		jibunAddress: '경기 성남시 분당구 삼평동 681'
}
*/

$(function() {
	$("#btn-search-postcode").click(function() {
		new daum.Postcode({
			oncomplete: function(data) {
				// 기본주소 정보를 대입하는 변수를 선언한다.
				let address;
				// 사용자가 도로명 주소를 선택했을 때 data.userSelectedType은 R이다.
				if (data.userSelectedType === 'R') {
					address = data.roadAddress;
				} else {
					address = data.jibunAddress;
				}
				
				// 우편번호 입력필드와 기본주소 입력필드에 값을 입력한다.
				$(":input[name=postcode]").val(data.zonecode);
				$(":input[name=address1]").val(address);
			
				// 상세주소 입력필드에 포커스를 위치시킨다.
				$(":input[name=address2]").focus();
			}
		}).open();
	});
})

```

