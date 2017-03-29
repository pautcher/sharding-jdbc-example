package com.bz.open.sharding.ucenter.dao.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

public class SingleKeyModuleTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String> {

	private Logger log = LoggerFactory.getLogger(SingleKeyModuleTableShardingAlgorithm.class);

	@Value("#{cfg['system.database.table.count']}")
	private int tableCount = 2;

	@Override
	public String doEqualSharding(final Collection<String> availableTargetNames,
			final ShardingValue<String> shardingValue) {
		for (String each : availableTargetNames) {
			String[] segs = each.split("_");
			if (Integer
					.parseInt(segs[segs.length - 1]) == (Math.abs(shardingValue.getValue().hashCode()) % tableCount)) {
				log.info("Hashing Table::" + each);
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
				String[] segs = tableNames.split("_");
				if (Integer.parseInt(segs[segs.length - 1]) == (Math.abs(value.hashCode()) % tableCount)) {
					result.add(tableNames);
				}
			}
		}
		return result;
	}

	@Override
	public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames,
			final ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		Range<String> range = shardingValue.getValueRange();
		// for (String i = range.lowerEndpoint(); i <= range.upperEndpoint();
		// i++) {
		// for (String each : availableTargetNames) {
		// if (each.endsWith(i % tableCount + "")) {
		// result.add(each);
		// }
		// }
		// }
		return result;
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
