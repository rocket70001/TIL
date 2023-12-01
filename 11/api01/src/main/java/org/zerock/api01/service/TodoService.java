package org.zerock.api01.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.dto.TodoDTO;

@Transactional
public interface TodoService {

    Long register(TodoDTO todoDTO);
}
