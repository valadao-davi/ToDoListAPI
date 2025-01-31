package com.valadao_davi.todolist.repositories;

import com.valadao_davi.todolist.entities.Task;
import com.valadao_davi.todolist.projections.TaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


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
            SELECT t.id_task AS idTask,
                   t.name_task AS nameTask,
                   t.status AS status,
                   t.priority AS priority,
                   t.duration_task AS durationTask
            FROM table_task t
            WHERE t.user_id = :userId;
            """)
    List<TaskProjection> tasksByUserId(Long userId);

    Optional<TaskProjection> findByidTask(Long taskId);


}
