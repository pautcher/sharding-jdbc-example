package com.bz.open.sharding.ucenter.dao.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

@Component
public class SingleKeyModuleDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<String> {

	@Value("#{cfg['system.database.count']}")
	private Integer dbCount = 2;

	@Override
	public String doEqualSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
		for (String each : tableNames) {
			if (each.endsWith(Math.abs(shardingValue.hashCode()) % dbCount + "")) {
				return each;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(tableNames.size());
		for (String value : shardingValue.getValues()) {
			for (String tableName : tableNames) {
				if (tableName.endsWith(Math.abs(value.hashCode()) % dbCount + "")) {
					result.add(tableName);
				}
			}
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
		return null;
	}

	public void setDbCount(Integer dbCount) {
		this.dbCount = dbCount;
	}

}
