package com.lk.service;

import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

@Service
public class FlowService {
	
	/**
	 * blockHandler: 流控降级的时候进行的函数
	 * fallback: 抛出异常进入的函数
	 * 注意：流控规则也可以在sentinel控制台配置
	 * @return
	 */
	@SentinelResource(value = "com.lk.service.FlowService:flow",entryType = EntryType.OUT,blockHandler = "flowBlockHandler")
	public String flow() {
		System.out.println("正常执行flow");
		return "flow";
	}
	
	public String flowBlockHandler(BlockException ex) {
		System.out.println("触发流控策略" + ex);
		return "流控方法";
	}
}
