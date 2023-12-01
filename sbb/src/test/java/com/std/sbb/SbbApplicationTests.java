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
	@Test
	@DisplayName("데이터 조회하기")
	void test006() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}
	@Test
	@Transactional
	@DisplayName("답변에 연결된 질문 찾기")
	void test007() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 1번 질문의 답입니다", answerList.get(0).getContent());
	}
	@Test
	@DisplayName("답변 삭제하기")
	void test008() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		this.answerRepository.delete(a);
		assertEquals(0, this.answerRepository.count());
	}
	@Test
	@DisplayName("답변 생성")
	void test009() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 1번 질문의 답입니다");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	@Test
	@DisplayName("답변 데이터 조회")
	void test010() {
		Optional<Answer> oa = this.answerRepository.findById(2);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals("네 1번 질문의 답입니다", a.getContent());
	}
	@Test
	@Transactional
	@DisplayName("답변에 연결된 질문 찾기")
	void test011() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();
		assertEquals(1, answerList.size());
		assertEquals("네 1번 질문의 답입니다", answerList.get(0).getContent());
	}

	@Test
	@DisplayName("데이터 답변 수정")
	void test012() {
		Optional<Answer> oa = this.answerRepository.findById(2);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		a.setContent("수정된 1번 질문의 답입니다");
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	@Test
	@DisplayName("추가")
	void test013() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 1번 질문의 두번째 답입니다");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	@Test
	@DisplayName("질문 추가")
	void test014() {
		Question q3 = new Question();
		q3.setSubject("3번 질문");
		q3.setContent("3번 질문 내용");
		q3.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q3);

		Question q4 = new Question();
		q4.setSubject("4번 질문");
		q4.setContent("4번 질문의 내용");
		q4.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q4);
	}
	@Test
	@DisplayName("질문 조회")
	void test015() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(3, all.size());
		Question q = all.get(0);
		assertEquals("2번 질문", q.getSubject());
	}

	@Test
	@DisplayName("단건 조회")
	void test016() {
		List<Question> likeList =  this.questionRepository.findBySubjectLike("%질문%");
		Question q = likeList.get(0);
		assertEquals("2번 질문", q.getSubject());
	}
	@Test
	@DisplayName("답 생성")
	void test017() {
		Optional<Question> oq = this.questionRepository.findById(4);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("4번 질문의 대답");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}

	@Test
	@Transactional
	@DisplayName("질문에 해당되는 대답 조회")
	void test018() {
		Optional<Question> oq = this.questionRepository.findById(4);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();
		assertEquals(1, answerList.size());
		assertEquals("4번 질문의 대답", answerList.get(0).getContent());
	}

	@Test
	@DisplayName("생성")
	void test019() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("대답1");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	@Test
	@DisplayName("삭제")
	void test020() {
		Optional<Answer> oa = this.answerRepository.findById(9);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		this.answerRepository.delete(a);
		assertEquals(3, this.answerRepository.count());
	}
	@Test
	@DisplayName("전체 삭제 답변")
	void test021() {
		this.answerRepository.deleteAll();
	}

	@Test
	@DisplayName("생성")
	void test022() {
		Optional<Question> oa = this.questionRepository.findById(4);
		assertTrue(oa.isPresent());
		Question q = oa.get();

		Answer a = new Answer();
		a.setContent("4번 질문의 답");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());

		Answer a1 = new Answer();
		a1.setContent("4번 질문의 답2");
		a1.setQuestion(q);
		a1.setCreateDate(LocalDateTime.now());


		Answer a2 = new Answer();
		a2.setContent("4번 질문의 답3");
		a2.setQuestion(q);
		a2.setCreateDate(LocalDateTime.now());

		this.answerRepository.save(a);
		this.answerRepository.save(a1);
		this.answerRepository.save(a2);
	}
	@Test
	@DisplayName("테스트 데이터 생성")
	void test023() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content, null);
		}
	}
}
