package com.filmindustry.candidatescreening;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(Suite.class)
@SuiteClasses({ UserDetailsTest.class,
				DirectorPortalTest.class})
class CandidateScreeningApplicationTests {
}
