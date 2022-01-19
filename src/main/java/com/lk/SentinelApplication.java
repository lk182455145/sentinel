package com.lk;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@SpringBootApplication
public class SentinelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelApplication.class, args);
		initFlowRules();
	}
	
	private static void initFlowRules() {
		
		FlowRule rule = new FlowRule();
		rule.setResource("com.lk.service.FlowService:flow");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setCount(1);
		FlowRuleManager.loadRules(Collections.singletonList(rule));
		
	}
}
