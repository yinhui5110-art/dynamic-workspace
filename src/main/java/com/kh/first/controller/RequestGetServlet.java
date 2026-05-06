package com.kh.first.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/first.do")// Servlet == HTTP요청을 처리하는 Java클래스이다
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; //버전관리를 위해
       
    public RequestGetServlet() {
        super();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ㅎㅎ~"); //콘솔창에 "ㅎㅎ~"출력된다는 것은 doGet이라는 메소드가 호출되고 있는중
		/*
		 * 톰캣을 실행 => web.xml
		 * init() => service()=> doGet() => doPost() => destory() 여기까지 호출하고 소멸시킨다
		 *  우리는 서블릿 컨테이너(Tomcat)가 서블릿의 생명주기를 관리/ 싱글톤 패턴으로 객체 하나만을 사용한다
		 *  멀티스레드 처리
		 */
		
		/*
		 * Dynamic WebProject를 진행하는 동안은 Servlet을 Controller로 사용할 것
		 * 
		 * 1. 데이터 가공해야한다(view에서 할 수 없다)
		 * 2. 요청처리(Service단 호출)
		 * 3. 결과값 반환(응답화면 지정) /controller에서 view로 결과값 반환한다
		 * 
		 * view에서 Get방식으로 요청할 시 doGet() 호출된다!!
		 * 
		 */
		/*
		 * 매개변수 두 개 있다
		 * 첫 번째 매개변수 HttpServletRequest타입에 요청 시 전달된 내용들이 담김
		 * => 요청 전송 방식, 요청 URL, 요청한 사용자의 정보, 사용자가 input요소에 입력한 값 등...
		 * 
		 * 두 번째 매개변수 HttpServletResponse타입에는 요청 처리 후 응답할 때 사용하는 객체 
		 * 
		 * 요청 처리 스탭
		 * 
		 * 1. 요청을 처리하기 위해서 요청 시 전달된 값(사용자가 입력한 값)들을 뽑는다.
		 * =>input요소의 name속성값=input요소의value속성값
		 * => request의 Parameter라는 속성에 담겨있다 => 여기서 뽑아내야한다
		 * 
		 * 2. 뽑아낸 값을 가공해서 요청 처리를 진행해야한다(Service -> DAO -> DB)-> 오늘은 생략
		 * 
		 * 3. 처리 결과에 따라 성공 / 실패 응답
		 */
		
		/*
		 * request의 parameter에서 전달된 데이터 값을 뽑아내는 방법
		 * 
		 * - request가 가지고 있는 메소드를 호출해야하는데 메소드 중에 getParameter 
		 * 		request.getParameter("name속성값"): String
		 * => 반환타입이 String / 다른 자료형으로 사용해야 할 경우 parsing을 해주어야한다
		 * 
		 * -request.getParameterValues("name속성값") : String[]
		 * =>하나의 key값으로 여러 개의 value들을 받아야할 경우
		 */
		// 가장 먼저 해야할 일! => 값 뽑기
		String name = request.getParameter("name");
		System.out.println(name);
		// 경우의 수  "문자열의 값이기 때문에 셀 수 없는 데이터"
		// 정상적인 값을 입력했을 경우 -> "" (텍스트상자가 비어있을 경우 빈 문자열) /빈문자열이기 때문에 null 값이 나온다
		// name.length(); null값이 들어올수 있기 때문에 예외처리를 해줘야한다
		
		String gender = request.getParameter("gender");
		System.out.println(gender);
		//"셀 수 없음" /넘어오는 키값이 없으면 null
		
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println(age);
		// "" : NumberFormatException 발생
		
		String city = request.getParameter("city");
		System.out.println(city);
		//"서울" / "제주도"
		
		double height = Double.parseDouble(request.getParameter("height"));
		System.out.println(height);
		
		String[] foods = request.getParameterValues("food");
		System.out.println(Arrays.toString(foods));
		//null /["치킨", "피자"]
		
		//상태코드
		//200 : OK =>통신이 정상적으로 잘 이루어짐
		//404 : 파일 또는 요청을 받아주는 서블릿매핑값을 찾지 못했을 때 발생
		//		=> 경로를 잘 못 적었거나 오타 났을때
		//500 : 자바 소스코드 상의 오류(예외발생)
		
		//2단계 가공
		// Person person = new Person(name, gender, age, city, height, foods);
		//만들었다고 치고
		
		//3단계 요청 처리(Service단 호출)
		//INSERT라고 가정하고
		//Controller에서 Service객체를 생성한 후 Service단의 메소드를 호출 하면서 Person객체의 주소값을 전달 한뒤
		//컨트롤러는 서비스로부터 결과값을 반환받는다
		//int result = new PersonService().savePerson(person);
		// 무조건 성공했다고 침
		
		//4단계 결과값 반환 or 응답화면 지정
		
		//순수 Servlet만 사용해서 응답데이터 넘기기
		//사용자에게 HTML + CSS + JS응답
		/*
		 * 요청 처리에 성공했습니다!
		 * 
		 * xxx님은,
		 * xx살이며,
		 * xxx에 삽니다.
		 * 키는 xxxcm이고
		 * 
		 * 성별은 case 1. 선택을 하지 않았습니다
		 * 		case 2. 남성입니다.
		 * 		case 3. 여성입니다.
		 * 
		 * 좋아하는 음식은 case 1. 없습니다
		 * 				case 2. 치킨~ 피자~ 등등
		 * 
		 * 
		 * 
		 */
		
		// 1) 1단계 응답 데이터 형식 지정 -> 문서형태의 HTML / 인코딩 방식 UTF-8
		response.setContentType("text/html; charset=UTF-8");
		
		// 2) 2단계 출력 스트림 생성하기
		//	InputStream / OutputStream
		//	  Reader   /     Writer
		PrintWriter pw = response.getWriter();
		
		// 3) 스트림을 이용해서 HTML데이터 출력
		pw.println("<html>");
			pw.println("<head>");

			pw.println("<title>순수 서블릿으로 응답해보기!</title>");
			pw.println("<style>");
				pw.println("#name{color : orange}");
				pw.println("#age{color : orangered}");
				pw.println("#city{color :forestgreen}");
				pw.println("#height{color :green}");
				pw.println("#gender{color :gold}");
			pw.println("</style>");
			pw.println("</head>");
		
			pw.println("<body>");
			
			pw.println("<h1>요청처리에 성공했습니다</h1>");
			
			pw.printf("<span id='name'>%s</span>님은<br>",name);
			pw.printf("<span id='age'>%d</span>살이며,<br>",age);
			pw.printf("<span id='city'>%s</span>에 삽니다.<br>",city);
			pw.printf("키는 <span id='height'>%.1f</span><br><br>",height);
			
		
			pw.print("성별은 ");
			if(gender == null || "선택안함".equals(gender)) {
				pw.println("선택을 안했습니다.");
			}else if(gender.equals("남자")) {
				pw.println("<span id='gender'>남자</span>입니다.");
			}else {
				pw.println("<span id='gender'>여자</span>입니다.");
			}
			
			pw.print("좋아하는 음식은");
			if(foods == null) {
				pw.println("없습니다.");
			}else {
				pw.println("<ul>");
				
				for(int i= 0; i<foods.length; i++) {
					pw.printf("<li>%s</li>", foods[i]);
				}
				pw.println("</ul>");
			}
			pw.println("<script>");
				pw.println("alert('축하축하~~');");
			pw.println("</script>");	
		
		
			pw.println("</body>");
		pw.println("</html>");
		
		
		
		
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
