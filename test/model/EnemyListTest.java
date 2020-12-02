package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import org.junit.jupiter.api.Test;

class EnemyListTest {
	
private EnemyList list;
	
	public void stage1() {
		list=new EnemyList();
	}

	public void stage2() {
		list=new EnemyList();
		list.add(new Enemy(50, 50, 20, "chikenKing", true, 'D'));
		list.add(new Enemy(50, 50, 20, "chikenKing", true, 'D'));
		list.add(new Enemy(50, 50, 20, "chikenKing", true, 'D'));
	}
	
	@Test
	void clearTest() {
		stage2();
		list.clear();
		assertNull(list.getFirst());
	}
	
	@Test
	void getTest() {
		stage2();
		assertEquals(list.getFirst(), list.get(0));
	}
	
	@Test
	void addTest() {
		stage1();
		Enemy one=new Enemy(50, 50, 20, "chikenKing", true, 'D');
		Enemy two=new Enemy(50, 50, 20, "chikenKing", true, 'D');
		Enemy three=new Enemy(50, 50, 20, "chikenKing", true, 'D');
		
		list.add(one);
		list.add(two);
		list.add(three);
		
		assertEquals(one, list.getFirst());
		assertEquals(two, list.get(1));
		assertEquals(three, list.get(2));
		assertEquals(3, list.size());
	}

}
