package com.codeme.pro.interview.toast;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.Collection;
import java.util.List;

public class InheritedFields {

  static class Menu {

    public String name;
    public List<MenuGroup> groups;
    public Double price; // nullable

    public Menu(String name, Double price, List<MenuGroup> groups) {
      this.name = name;
      this.price = price;
      this.groups = groups;
    }
  }

  static class MenuGroup {

    public String name;
    public List<MenuGroup> groups;
    public List<MenuItem> items;
    public Double price; // nullable

    public MenuGroup(String name, Double price, List<MenuItem> items, List<MenuGroup> groups) {
      this.name = name;
      this.price = price;
      this.items = items;
      this.groups = groups;
    }
  }

  static class MenuItem {

    public String name;
    public Double price; // nullable

    public MenuItem(String name, Double price) {
      this.name = name;
      this.price = price;
    }
  }

  static class Result {

    boolean found;
    Double price;

    Result(boolean found, Double price) {
      this.found = found;
      this.price = price;
    }
  }

  public static Double getPrice(Collection<Menu> menus, MenuItem item) {
    for (Menu menu : menus) {
      Double menuPrice = menu.price;

      for (MenuGroup group : menu.groups) {
        Result result = getItemPrice(group, item);
        if (result != null) {
          return result.found && result.price != null ? result.price : menuPrice;
        }
      }
    }

    throw new RuntimeException("TODO: Implement this method!");
  }

  static Result getItemPrice(MenuGroup group, MenuItem item) {
    List<MenuGroup> subGroup = group.groups;
    Double groupPrice = group.price;
    Double itemPrice = null;

    if (!subGroup.isEmpty()) {
      for (MenuGroup menuGroup : subGroup) {
        return getItemPrice(menuGroup, item);
      }
    } else {
      for (MenuItem i : group.items) {
        if (i.name.equals(item.name)) {
          if (i.price != null) {
            itemPrice = i.price;
          }
          return new Result(true, itemPrice != null ? itemPrice : groupPrice);
        }
      }
    }
    return null;
  }

  static final Menu LUNCH_MENU, DRINK_MENU;

  public static void main(String args[]) {
    // you may use this method to run test cases/debug
    List<Menu> menus = List.of(LUNCH_MENU, DRINK_MENU);

    System.out.println(getPrice(menus, new MenuItem("Coke", null)));
    System.out.println(getPrice(menus, new MenuItem("Cobb Salad w/ Bacon", null)));
  }

  static {
    // Initialize LUNCH_MENU + DRINK_MENU in a block so that it's collapsible in IDE
    LUNCH_MENU = new Menu(
        "Lunch",
        11.50,
        asList(
            new MenuGroup(
                "Appetizers",
                5.00,
                asList(new MenuItem("Wings", null), new MenuItem("Fries", 3.50)),
                emptyList()
            ),
            new MenuGroup(
                "Entrees",
                null,
                asList(new MenuItem("Burger", null)),
                asList(
                    new MenuGroup(
                        "Salads",
                        7.50,
                        asList(
                            new MenuItem("Garden Salad", null),
                            new MenuItem("Cobb Salad w/ Bacon", 9.00)),
                        emptyList()
                    ),
                    new MenuGroup(
                        "Toast",
                        6.50,
                        asList(
                            new MenuItem("Avocado", null),
                            new MenuItem("PB&J", null)),
                        emptyList()
                    )
                )
            )
        )
    );

    DRINK_MENU = new Menu(
        "Drinks",
        5.00,
        asList(
            new MenuGroup(
                "Coffees",
                null,
                emptyList(),
                asList(
                    new MenuGroup(
                        "Iced",
                        5.00,
                        asList(
                            new MenuItem("Iced Latte", 6.50),
                            new MenuItem("Decaf Iced Coffee", null)),
                        emptyList()
                    ),
                    new MenuGroup(
                        "Hot",
                        3.00,
                        asList(
                            new MenuItem("Americano", null),
                            new MenuItem("Cup of Joe", null)
                        ),
                        emptyList()
                    )
                )
            ),
            new MenuGroup(
                "Sodas",
                2.50,
                asList(new MenuItem("Coke", null), new MenuItem("Sprite", null),
                    new MenuItem("Ginger Ale", null)),
                emptyList()
            )
        )
    );
  }
}
