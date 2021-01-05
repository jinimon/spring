package co.company.spring.users.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.company.spring.users.service.UserService;
import co.company.spring.users.service.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

	@Override
	public List<UserVO> getUserList(UserVO vo) {
		return userDAO.getUserList(vo);
	}

	@Override
	public List<Map> getUserListMap(UserVO vo) {
		return userDAO.getUserListMap(vo);
	}

	//@Transactional
	public int insertUser(UserVO dto) {
		userDAO.insertUser(dto);
		return userDAO.insertUser(dto);
	}

	public int updateUser(UserVO dto) {
		return userDAO.updateUser(dto);
	}

	public int deleteUser(UserVO dto) {
		return userDAO.deleteUser(dto);
	}
}
