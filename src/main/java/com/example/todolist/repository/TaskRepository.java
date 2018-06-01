package com.example.todolist.repository;

import com.example.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM task t WHERE t.user_id =:user_id")
    List<Task> findTasksByUserId(@Param("user_id") int userId);

    @Query(nativeQuery = true, value = "SELECT * FROM task t WHERE t.id =:task_id")
    Task findTaskByTaskId(@Param("task_id") int taskId);

    @Transactional
    void deleteByTaskId(int taskId);

    Task findTop1ByOrderByTaskIdDesc();


}
