package org.zerock.api01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.api01.domain.QTodo;
import org.zerock.api01.domain.Todo;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.TodoDTO;

import java.util.List;

import static org.zerock.api01.domain.QTodo.todo;

public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<TodoDTO> list(PageRequestDTO pageRequestDTO) {

        QTodo qTodo = todo;

        JPQLQuery<Todo> query = from(todo);

        if(pageRequestDTO.getFrom() != null && pageRequestDTO.getTo() != null) {

            BooleanBuilder fromToBuilder = new BooleanBuilder();
            fromToBuilder.and(todo.dueDate.goe(pageRequestDTO.getFrom()));
            fromToBuilder.and(todo.dueDate.loe(pageRequestDTO.getTo()));
            query.where(fromToBuilder);
        }

        if(pageRequestDTO.getCompleted() != null) {
            query.where(todo.complete.eq(pageRequestDTO.getCompleted()));
        }

        if(pageRequestDTO.getCompleted() != null) {
            query.where(todo.title.eq(pageRequestDTO.getKeyword()));
        }

        //this 키워드는 메소드가 사용되는 시점의 객체 자신을 가리킨다.
        this.getQuerydsl().applyPagination(pageRequestDTO.getPageable("tno"), query);

        JPQLQuery<TodoDTO> dtoQuery = query.select(Projections.bean(TodoDTO.class,
                todo.tno,
                todo.title,
                todo.dueDate,
                todo.complete,
                todo.writer));

        List<TodoDTO> list = dtoQuery.fetch();

        long count = dtoQuery.fetchCount();

        return new PageImpl<>(list, pageRequestDTO.getPageable("tno"), count);

    }
}
