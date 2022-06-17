package com.tencoding.blog.contoller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	// UserRepository 메모리에 올라가있는 상태.
	// 그럼 어떻게 가져 오나요? DI
	@Autowired // 자동으로 new 때려 준다. (레퍼런스변수가 담겨짐)
	private UserRepository userRepository;
	
	// REST POST
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		System.out.println("========================");
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println("========================");
		
		System.out.println(user.getId());
		System.out.println(user.getCreateDate());
		System.out.println(user.getRole()); // null 이지만 default 값이 안먹힌다!
		
		//해결
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입 완료 되었습니다.";
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
//		 Optional --> Optional 로 감싸서 user Entity를 가지고 오겠다.
//		User userTemp1 = userRepository.findById(id).get();
		
//		 없으면 User을 리턴해라
//		User user = userRepository.findById(id).orElseGet(() -> {
//			return new User();
//		});
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없는 사용자 입니다 : " + id);
		});
//		User user = userRepository.findById(id);
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> 전체사용자검색(){
		// finAll에 List<User>가 담겨져있다.
		return userRepository.findAll();
	}
	
	//paging처리
	// http://localhost:9090/blog/dummy/user?page=0
	// 매게변수를 쿼리 파라미터로 받을거임!
	// 한 페이지의 사이즈는 2
	// 정렬은 id로 할꺼다
	//Direction.DESC 최신값
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC)Pageable pageable){
		
	 // 둘중에 원하는걸로 return 하면된다.
//		Page<User> pageuser = userRepository.findAll(pageable);
//		List<User> user = userRepository.findAll(pageable).getContent();
		
		Page<User> pageuser = userRepository.findAll(pageable);
//		List<User> user = page.getContent();
		return pageuser;
	}
	
	// 더티체킹하는 어노테이션
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + reqUser.getPassword());
		System.out.println("email : " + reqUser.getEmail());
		// 받은걸 DB에 저장 해줘야한다. // 오류 뜬이유는 userName이 없어서 오류가뜸! 그래서 select를 해와서 추가적인 처리를 해줘야 수정이 된다.
		// select 해주는 부분
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없는 사용자 입니다." + id);
		});
//		reqUser.setUserName(userEntity.getUserName());
		
		userEntity.setPassword(reqUser.getPassword());
		userEntity.setEmail(reqUser.getEmail());
		// 가능한 save 함수를 안쓸꺼다 (더티체킹개념 때문에
//		User user = userRepository.save(userEntity);
		return null;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		// 예외처리
		try {
			userRepository.deleteById(id);	
		} catch (Exception e) {
			return "해당유저는 없는 유저입니다.";
		}
		
		return "id : " + id + " 는 삭제 되었습니다.";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
