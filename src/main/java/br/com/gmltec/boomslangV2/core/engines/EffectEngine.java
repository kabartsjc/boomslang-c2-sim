package br.com.gmltec.boomslangV2.core.engines;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.gmltec.boomslangV2.core.utils.GeoUtils;
import br.com.gmltec.boomslangV2.entities.IEntity;
import br.com.gmltec.boomslangV2.entities.IEntity.STATUS;
import br.com.gmltec.boomslangV2.entities.sensors.ISensor;
import br.com.gmltec.boomslangV2.entities.weapons.IWeapon;
import br.com.gmltec.boomslangV2.plan.ITask;

public class EffectEngine {

	public static void mobile_effect(IEntity entity, Map<String, IEntity> entDB, long time_diff) {
		ITask task = entity.getCurrentTask();
		if (task == null) {
			entity.nextTask();

			if (entity.getBehaviorMode() == 2) {
				static_effect(entity, entDB);
			}

		} else if (task.getType().equals("PATROL")) {
			static_effect(entity,entDB);
		}
		
		else {
			IEntity enemy = task.getTarget();
			if (enemy==null)
				System.out.println(entity.getId());
			List<IWeapon> weaponList = entity.getWeaponList();
			if (weaponList.size() > 0) {
				for (ISensor sensor : entity.getSensors()) {
					if (sensor.isSense(entity, enemy)) {
						for (IWeapon wp : weaponList) {
							int qtd = entity.getWeaponQuantity(wp);
							if (qtd > 0) {
								double range = GeoUtils.calculateHorizontalDistanceMeters(entity.getCurrentPosition(),
										enemy.getCurrentPosition());
								double wp_range = wp.getRange();

								if (wp_range >= range) {
									entity.decrementWeapon(wp);
									engage(entity, wp, enemy, task);
								}
							}
						}

					}

				}

			}

		}

	}

	public static void static_effect(IEntity entity, Map<String, IEntity> entDB) {
		List<IWeapon> weaponList = entity.getWeaponList();
		if (weaponList.size() > 0) {
			for (ISensor sensor : entity.getSensors()) {
				List<IEntity> enemyL = new ArrayList<IEntity>(entDB.values());
				for (IEntity enemy : enemyL) {
					if (sensor.isSense(entity, enemy)) {
						for (IWeapon wp : weaponList) {
							int qtd = entity.getWeaponQuantity(wp);
							if (qtd > 0) {
								double range = GeoUtils.calculateHorizontalDistanceMeters(entity.getCurrentPosition(),
										enemy.getCurrentPosition());
								double wp_range = wp.getRange();

								if (wp_range >= range) {
									entity.decrementWeapon(wp);
									engage(entity, wp, enemy, null);
								}
							}
						}
					}

				}

			}

		}

	}

	private static void engage(IEntity attacker, IWeapon wp, IEntity defender, ITask task) {
		Random rand = new Random(System.currentTimeMillis());
		double f_attacker = attacker.getEntType().getLethalityFactor() * wp.getIntensity() * wp.getPrecision()
				* rand.nextDouble();

		double f_defensor = 0;
		IWeapon def_wp = getWeapon(defender, 0);
		if (def_wp != null) {
			f_defensor = defender.getEntType().getResilianceFactor() * def_wp.getReliability()
					/ Math.pow(2, defender.getEntType().getVulnerabilityFactor()) * rand.nextDouble();
		}

		double result = f_attacker - f_defensor;
		if (result > 0) {
			double health = result - defender.getHealth();
			defender.setHealth(health);
			if (health < 0.1) {
				defender.setStatus(STATUS.DESTROYED);
				System.out.println(defender.getId()+"DESTROYED by " +attacker.getId());
				if (task != null) {
					task.setStatus(ITask.STATUS.SUCESSFUL);
					attacker.nextTask();
				}
			}

			if (task != null && health >= 0.1) {
				if (health < (1 - task.getEffectiveness())) {
					task.setStatus(ITask.STATUS.SUCESSFUL);
					attacker.nextTask();
				} else {
					task.setStatus(ITask.STATUS.FAIL);
					attacker.nextTask();
				}
			}
		}

		else {
			double health = result - attacker.getHealth();
			attacker.setHealth(health);
			if (health < 0.1) {
				attacker.setStatus(STATUS.DESTROYED);
				System.out.println(attacker.getId()+"DESTROYED by " +defender.getId());
				if (task != null)
					task.setStatus(ITask.STATUS.FAIL);
			}
		}
	}

	private static IWeapon getWeapon(IEntity entity, double desired_range) {
		for (IWeapon wp : entity.getWeaponList()) {
			int qtd = entity.decrementWeapon(wp);
			double weapon_range = wp.getRange();
			if (qtd > 0 && weapon_range >= desired_range) {
				return wp;
			}
		}
		return null;
	};

}
