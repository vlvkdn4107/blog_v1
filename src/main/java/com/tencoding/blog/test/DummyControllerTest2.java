package com.tencoding.blog.test;

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
public class DummyControllerTest2 {
		// UserRepository
		@Autowired
		private UserRepository repository;
		// 회원가입
		@PostMapping("/dummy2/join")
		public String join(@RequestBody User user) {
			user.setRole(RoleType.USER);
			repository.save(user);
			return "회원가입 성공!";
		}
		// select
		@GetMapping("/dummy2/user/{id}")
		public User detail(@PathVariable int id) {
			User user = repository.findById(id).orElseThrow(() -> {
				return new IllegalArgumentException("해당 유저는 없는 사용자 입니다." + id);
			});
			return user;
		}
		// selectAll
		@GetMapping("/dummy2/userAll")
		public List<User> userAll(){
			return repository.findAll();
		}
		// select paging 리턴 타입을 Page로 할때
		@GetMapping("/dummy2/userpaging")
		public Page<User> pageList(@PageableDefault(size = 3, sort = "id", direction = Direction.DESC)Pageable pageable){
			Page<User> pageuser = repository.findAll(pageable);
			return pageuser;
		}
		@GetMapping("/dummy2/userpaging2")
		public List<User> pagelist(@PageableDefault(size = 3, sort = "id", direction = Direction.DESC)Pageable pageable){
			Page<User> pageUser = repository.findAll(pageable);
			List<User> user = repository.findAll(pageable).getContent();
			return user;
		}
		
		// update (더티체킹
		@Transactional
		@PutMapping("/dummy2/userUpdate/{id}")
		public User updateuser(@PathVariable int id, @RequestBody User reqbodyuser) {
			User userentity = repository.findById(id).orElseThrow();
			userentity.setPassword(reqbodyuser.getPassword());
			userentity.setEmail(reqbodyuser.getEmail());
			return null;
		}
		
		// delete
		@DeleteMapping("/dummy2/userdelete/{id}")
		public String deleteuser(@PathVariable int id) {
			try {
				repository.deleteById(id);	
			} catch (Exception e) {
				return "해당 유저는 없습니다";
			}
			
			return null;
		}
}
