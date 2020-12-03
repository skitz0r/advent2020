fun getInputForDay(day: Int): Collection<String> =
    SolverOneTest::class.java.getResource("/inputs/input$day").openStream().reader().readLines()