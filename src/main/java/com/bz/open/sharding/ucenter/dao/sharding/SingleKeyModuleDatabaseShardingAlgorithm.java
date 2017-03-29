package com.bz.open.sharding.ucenter.dao.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

@Component
public class SingleKeyModuleDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<String> {

	private Logger log = LoggerFactory.getLogger(SingleKeyModuleDatabaseShardingAlgorithm.class);

	@Value("#{cfg['system.database.count']}")
	private Integer dbCount = 2;

	@Override
	public String doEqualSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {

		for (String each : tableNames) {
			String[] segs = each.split("_");
			if (Integer.parseInt(segs[segs.length - 1]) == (Math.abs(shardingValue.getValue().hashCode()) % dbCount)) {
				log.info("Hashing DB::" + each);
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
				String[] segs = tableName.split("_");
				if (Integer.parseInt(segs[segs.length - 1]) == (Math.abs(value.hashCode()) % dbCount)) {
					result.add(tableName);
				}
			}
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(tableNames.size());
		Range<String> range = (Range<String>) shardingValue.getValueRange();
		// for (String i = range.lowerEndpoint(); i <= range.upperEndpoint();
		// i++) {
		// for (String each : tableNames) {
		// if (each.endsWith(i % dbCount + "")) {
		// result.add(each);
		// }
		// }
		// }
		return result;
	}

	public void setDbCount(Integer dbCount) {
		this.dbCount = dbCount;
	}

}
