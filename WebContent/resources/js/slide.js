/**
 * 
 */


		/* cnt 값을 즉시실행함수로 감싸서, 전역에서 격리시킨다. 이벤트리스너에 등록된 콜백함수들이 closure 역할을 한다.*/
		// 즉시실행함수가 생성되면서, 스택영역에 cnt를 올린다.
		(function(){
			let cnt = 0;
			$('.arrow-right').addEventListener('click', ()=>{      //이 콜백함수의 상위스코프는 즉시실행함수이다.. 그러므로, 클로저를 통해서 이미 종료된 즉시실행함수의 cnt에 접근 핤 수 있다.
				cnt++;   //cnt값을 늘려감.
				if(cnt>3){
					cnt=0;  //cnt가 3을 넘어가면 0으로 되돌림(translateX가 0도가 되므로, 다시 원빈(초기값)으로 돌아오게 됨)
				}
				moveSlide(cnt);
			})
			

			$('.arrow-left').addEventListener('click', ()=>{
				cnt--;
				if(cnt<0){
					cnt=3;  //cnt가 0보다 작아지면, 마지막 페이지로 되돌림.
				}
				moveSlide(cnt);
			});
		})();
		
		/* 
		document.querySelectorAll('.btn_slide>button').forEach((e)=>{
			e.addEventListener('click',(event)=>{
				let slideId = event.target.id;
				slideId = slideId.charAt(slideId.length-1);
				moveSlide(slideId);
			})
		})
		 */
		 // data를 사용하지 않은것(아직 버튼에 아이디속성이 걸려있는 경우)
		
		document.querySelectorAll('.btn_slide>button').forEach((e)=>{
			e.addEventListener('click',(event)=>{
				let slideId = event.target.dataset.slideIdx;
				moveSlide(slideId);
			})
		})
		
		
		let moveSlide = (cnt) => {
			document.querySelectorAll('.slide>li').forEach((e)=>{  //queryselectAll 로 li의 코드들을 받아오고 준 객체로 만듦, 그를 포이치로 돌림. /* queryselectAll 이 자식요소들을 전부 대려올 것임(li) */
				e.style.transform=`translateX(${cnt * -100}%)`;    //그 받아온 코드별로 트랜스펌 시킴.
				e.style.transitionDuration = '0.5s';
			})
		}
	