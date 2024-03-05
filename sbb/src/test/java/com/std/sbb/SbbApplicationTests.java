package com.std.sbb;

import com.std.sbb.answer.Answer;
import com.std.sbb.answer.AnswerRepository;
import com.std.sbb.question.Question;
import com.std.sbb.question.QuestionRepository;
import com.std.sbb.question.QuestionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.util.Linqy;

import java.lang.reflect.AnnotatedWildcardType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService questionService;
    @Test
    @DisplayName("create")
    void test001() {
        Question q1 = new Question();
        q1.setSubject("1번 질문");
        q1.setContent("1번 질문 내용");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("2번 질문");
        q2.setContent("2번 질문 내용");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);
    }
    @Test
    @DisplayName("데이터 조회하기")
    void test002() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("1번 질문", q.getSubject());
    }
    @Test
    @DisplayName("수정하기")
    void test003() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 1번 질문");
        q.setContent("수정된 1번 질문 내용");
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
    @Test
    @DisplayName("삭제하기")
    void test004() {
        Optional<Question> oq = this.questionRepository.findById(6);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(3, this.questionRepository.count());
    }
    @Test
    @DisplayName("답변데이터 생성 및 저장")
    void test005() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 1번 질문의 답입니다");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }
}
