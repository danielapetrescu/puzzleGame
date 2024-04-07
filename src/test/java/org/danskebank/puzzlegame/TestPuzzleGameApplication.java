package org.danskebank.puzzlegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestPuzzleGameApplication {

	public static void main(String[] args) {
		SpringApplication.from(PuzzleGameApplication::main).with(TestPuzzleGameApplication.class).run(args);
	}

}
