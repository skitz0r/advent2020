fun getInputForDay(day: Int): Collection<String> =
    SolverOneTest::class.java.getResource("/input1$day").openStream().reader().readLines()