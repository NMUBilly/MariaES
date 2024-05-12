package io.hansan.mariaes.user.database.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：何汉叁
 * @date ：2024/4/25 18:17
 * @description：TODO
 */
@Data
@Entity
@Table(name = "users")
@SQLDelete(sql  = "UPDATE users set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private Long classId;

    @Column
    private String classname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "create_at",updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "update_at")
    private Date updatedAt;

    @ColumnDefault("false")
    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
