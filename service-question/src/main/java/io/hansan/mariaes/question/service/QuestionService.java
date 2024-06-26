package io.hansan.mariaes.question.service;

import io.hansan.mariaes.question.data.vo.QuestionVo;
import io.hansan.mariaes.question.database.entity.QuestionEntity;
import io.hansan.mariaes.question.database.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/4/23 20:19
 * @description：TODO
 */
@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    public List<QuestionVo> getAllQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size, Sort.by("created_at").descending())).stream()
                .map(QuestionVo::fromEntity)
                .toList();
    }

    public void deleteQuestion(Long id) {
        questionRepository.updateDeleteId(id);
    }

    public QuestionVo getQuestion(Long id) {
        return questionRepository.findById(id)
                .map(QuestionVo::fromEntity)
                .orElseThrow();
    }

    public void addQuestion( QuestionEntity questionEntity) {
        questionRepository.save(questionEntity);
    }
}
