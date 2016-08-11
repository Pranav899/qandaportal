package com.alacriti.qandaportal.vo;

import java.util.List;

public class QuestionPage {
		public QuestionPage(){
			
		}
		private List<Question> questions;
		private List<Page> pages;
		public QuestionPage(List<Question> questions, List<Page> pages) {
			super();
			this.questions = questions;
			this.pages = pages;
		}
		public List<Question> getQuestions() {
			return questions;
		}
		public void setQuestions(List<Question> questions) {
			this.questions = questions;
		}
		public List<Page> getPages() {
			return pages;
		}
		public void setPages(List<Page> pages) {
			this.pages = pages;
		}
		
}