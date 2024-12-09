@PostMapping("/home")
public String search(@RequestParam String query, Model model) {
    // Здесь должна быть логика поиска. В данном примере мы просто создадим фиктивные результаты.
    List<String> results = new ArrayList<>();
    if (query != null && !query.isEmpty()) {
        results.add("Результат 1 для " + query);
        results.add("Результат 2 для " + query);
        results.add("Результат 3 для " + query);
    }

    model.addAttribute("query", query);
    model.addAttribute("results", results);
    model.addAttribute("username", registeredUser.getUsername());

    return "home";
}
