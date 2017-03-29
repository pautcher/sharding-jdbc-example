package com.bz.open.sharding.ucenter.dao.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

@Component
public class SingleKeyModuleTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String> {

	@Value("#{cfg['system.database.table.count']}")
	private int tableCount = 2;

	@Override
	public String doEqualSharding(final Collection<String> availableTargetNames,
			final ShardingValue<String> shardingValue) {
		for (String each : availableTargetNames) {
			if (each.endsWith(Math.abs(shardingValue.getValue().hashCode()) % tableCount + "")) {
				return each;
			}
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> doInSharding(final Collection<String> availableTargetNames,
			final ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		Collection<String> values = shardingValue.getValues();
		for (String value : values) {
			for (String tableNames : availableTargetNames) {
				if (tableNames.endsWith(Math.abs(value.hashCode()) % tableCount + "")) {
					result.add(tableNames);
				}
			}
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames,
			final ShardingValue<String> shardingValue) {
		return null;
	}

	/**
	 * 设置分表的个数
	 *
	 * @param tableCount
	 */
	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}

}
