package com.codeWithRaman.implementation.controller;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AIController {
	
	private ChatModel chatModel;
	
	public AIController(ChatModel chatModel) {
		this.chatModel = chatModel;
	}
	
	@GetMapping("/sayHello")
	public String sayHelloDecision() {
		PromptTemplate promptTemplate = new PromptTemplate("Answer with only 'yes' or 'no' {question}");
		Prompt prompt = promptTemplate.create(Map.of("question","Is this a long Weekend in India?"));
		String output  = chatModel.call(prompt).getResult().toString();
		if(output.contains("Yes")) {
			return "weekendHello";
		}else {
			return "noWeekendHello";
		}
	}
	
	@GetMapping("/hello")
	public String sayHelloDecisionBasisUserInput(@RequestParam String input) {
		PromptTemplate promptTemplate = new PromptTemplate("Answer with only 'yes' or 'no' {question}");
		Prompt prompt = promptTemplate.create(Map.of("question",input));
		String output  = chatModel.call(prompt).getResult().toString();
		if(output.contains("Yes")) {
			return "weekendHello";
		}else {
			return "noWeekendHello";
		}
	}
	

}
