package com.lk.test;

import java.util.Collections;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;

/**
 * 参考文档：<a href="https://github.com/alibaba/Sentinel/wiki/介绍">Sentinel官方中文文档</a>
 * @author lk
 *
 */
public class SentinelFlow {
	
	private static final String RESOURCE = "hello";

	public static void main(String[] args) throws InterruptedException {
		
		initFlowRules();
		
		// 定义资源
		while(true) {
			
			Entry entry = null;
			
			try {
				// 定义资源名称
				entry = SphU.entry(RESOURCE);
				
				// 逻辑代码
				System.out.println("hello word!!!");
				
				Thread.sleep(20L);
				
			}catch (BlockException e) {
				
				System.err.println("流控逻辑处理代码...");
				Thread.sleep(500L);
			}finally {
				if(entry != null) {
					entry.exit();
				}
			}
		}
	}
	
	/**
	 * sentinel的5种主流控制策略
	 * 
	 * <br/>
	 * 
	 * 规则：
	 * <ul>
	 * <li>流量控制：{@link FlowRule}</li>
	 * <li>熔断降级：{@link DegradeRule}</li>
	 * <li>系统自适应限流：{@link SystemRule}</li>
	 * <li>黑白名单控制：{@link AuthorityRule}</li>
	 * <li>热点参数限流：{@link ParamFlowRule}</p>
	 * </ul>
	 * 
	 * 管理器：
	 * <ul>
	 * <li>流量控制：{@link FlowRuleManager}</li>
	 * <li>熔断降级：{@link DegradeRuleManager}</li>
	 * <li>系统自适应限流：{@link SystemRuleManager}</li>
	 * <li>黑白名单控制：{@link AuthorityRuleManager}</li>
	 * <li>热点参数限流：{@link ParamFlowRuleManager}</li>
	 * </ul>
	 * 
	 * 异常：
	 * <ul>
	 * <li>流量控制：{@link FlowException}</li>
	 * <li>熔断降级：{@link DegradeException}</li>
	 * <li>系统自适应限流：{@link SystemBlockException}</li>
	 * <li>黑白名单控制：{@link AuthorityException}</li>
	 * <li>热点参数限流：{@link ParamFlowException}</li>
	 * </ul>
	 */
	private static void initFlowRules() {
		FlowRule rule = new FlowRule();
		// 资源名 注意：我们的规则一定要绑定到对应名称的资源上
		rule.setResource(RESOURCE);
		// 限流阈值类型
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// 限流阈值
		rule.setCount(20);
		FlowRuleManager.loadRules(Collections.singletonList(rule));
		
	}

}
