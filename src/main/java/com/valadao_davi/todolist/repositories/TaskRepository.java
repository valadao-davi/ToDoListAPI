package com.valadao_davi.todolist.repositories;

import com.valadao_davi.todolist.dto.TaskUsernamesDTO;
import com.valadao_davi.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query(nativeQuery = true, value = """
            UPDATE table_task
            SET
               status = :status,
               duration_task = CASE
                            WHEN :time IS NOT NULL THEN :time
                            ELSE duration_task
                           END
            WHERE
                id_task = :taskId;
            """)
    int updateProgress(Long taskId, String status, Double time);


    @Query(nativeQuery = true, value = """
            SELECT t.id_task AS taskId, t.nameTask, t.status, t.priority, t.duration, u.user_name AS userName
            FROM table_task t
            JOIN table_user u ON t.user_id = u.user_id
            WHERE u.user_id = :userId;
            """)
    List<TaskUsernamesDTO> tasksByUserId(Long userId);

}
