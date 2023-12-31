package com.sstixbackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sstixbackend.core.request.EventsSaveRequest;
import com.sstixbackend.core.request.EventsUpdateRequest;
import com.sstixbackend.core.response.EventsSelectResponse;
import com.sstixbackend.core.response.RestfulResponse;
import com.sstixbackend.model.Events;
import com.sstixbackend.model.Users;
import com.sstixbackend.repository.EventsRepository;
import com.sstixbackend.repository.UsersRepository;
import com.sstixbackend.util.JWTUtil;

import jakarta.security.auth.message.AuthException;

@Service
@Transactional
public class EventsService {

	@Autowired
	private EventsRepository er;

	@Autowired
	private UsersRepository ur;

	@Autowired
	private JWTUtil jwt;

	public ResponseEntity<RestfulResponse<?>> selectAllEvents(String keyword, Integer status) {
		Specification<Events> spec = Specification.where(null);
		if (keyword != null) {
			spec = spec.and((root, query, cb) -> cb.or(cb.like(root.get("name"), "%" + keyword + "%"),
					cb.like(root.get("details"), "%" + keyword + "%")));
		}

		if (status != null && status != 0) {
			spec = spec.and((root, query, cb) -> cb.notEqual(root.get("status"), 0));
		}

		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		List<Events> events = er.findAll(spec, sort);

		if (events != null) {
			List<EventsSelectResponse> responseList = events.stream().map(event -> {
				EventsSelectResponse response = new EventsSelectResponse(event.getId(), event.getName(),
						event.getDetails(), event.getLocation(), event.getOrganizer(), event.getEventDate(),
						event.getStatus(), event.getPrice(), event.getQty(), event.getImage1());
				return response;
			}).collect(Collectors.toList());

			RestfulResponse<List<EventsSelectResponse>> response = new RestfulResponse<List<EventsSelectResponse>>(
					"00000", "成功", responseList);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		RestfulResponse<String> response = new RestfulResponse<String>("00016", "資料錯誤", null);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	public ResponseEntity<RestfulResponse<?>> selectEvents(Integer id) {
		if (id != null) {
			Optional<Events> optional = er.findById(id);
			if (optional.isPresent()) {
				Events event = optional.get();
				EventsSelectResponse selectResponse = new EventsSelectResponse(event.getId(), event.getName(),
						event.getDetails(), event.getLocation(), event.getOrganizer(), event.getEventDate(),
						event.getStatus(), event.getPrice(), event.getQty(), event.getImage1());

				RestfulResponse<EventsSelectResponse> response = new RestfulResponse<EventsSelectResponse>("00000",
						"成功", selectResponse);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		}
		RestfulResponse<String> response = new RestfulResponse<String>("00016", "資料錯誤", null);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	public ResponseEntity<RestfulResponse<?>> saveEvents(EventsSaveRequest request, String auth) {
		String token = auth.substring(6);
		Integer id;
		try {
			id = jwt.validateToken(token);
		} catch (AuthException e) {
			RestfulResponse<String> response = new RestfulResponse<String>("00004", e.getMessage(), null);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}

		Optional<Users> optional = ur.findById(id);
		if (optional.isPresent()) {
			Users user = optional.get();
			if (user.getLevel() != 2) {
				RestfulResponse<String> response = new RestfulResponse<String>("00005", "沒有管理員權限", null);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
			}
		}

		if (request != null) {
			Events event = Events.builder().name(request.name()).details(request.details()).location(request.location())
					.organizer(request.organizer()).status(request.status()).price(request.price()).qty(request.qty())
					.eventDate(request.eventDate()).image1(request.image1()).build();
			er.save(event);
			RestfulResponse<String> response = new RestfulResponse<String>("00000", "新增成功", null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		RestfulResponse<String> response = new RestfulResponse<String>("00012", "新增失敗", null);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	public ResponseEntity<RestfulResponse<?>> updateEvents(EventsUpdateRequest request, String auth) {
		String token = auth.substring(6);
		Integer id;
		try {
			id = jwt.validateToken(token);
		} catch (AuthException e) {
			RestfulResponse<String> response = new RestfulResponse<String>("00004", e.getMessage(), null);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}

		Optional<Users> optional = ur.findById(id);
		if (optional.isPresent()) {
			Users user = optional.get();
			if (user.getLevel() != 2) {
				RestfulResponse<String> response = new RestfulResponse<String>("00005", "沒有管理員權限", null);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
			}
		}

		if (request != null && request.id() != null) {
			Optional<Events> original = er.findById(request.id());
			if (original.isPresent()) {
				Events event = original.get();
				event.setName(request.name());
				event.setDetails(request.details());
				event.setLocation(request.location());
				event.setOrganizer(request.organizer());
				event.setStatus(request.status());
				event.setPrice(request.price());
				event.setQty(request.qty());
				event.setEventDate(request.eventDate());
				if (request.image1() != null && !request.image1().equals(""))
					event.setImage1(request.image1());
				RestfulResponse<String> response = new RestfulResponse<String>("00000", "修改成功", null);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		}
		RestfulResponse<String> response = new RestfulResponse<String>("00015", "修改失敗", null);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
}
