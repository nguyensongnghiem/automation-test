@githubInfo

Feature: Verify Github info
  @no_browser_needed
  Scenario: TC-1: Get Github repositories
    And I call Github Api to get total open issues

  @no_browser_needed
  Scenario: TC-2: Get Github most watched repository
    And I call Github Api to get most watched repository

  @no_browser_needed
  Scenario: TC-2: Get Github repositories as updated descending sort
    And I call Github Api to get repositories as updated descending sort