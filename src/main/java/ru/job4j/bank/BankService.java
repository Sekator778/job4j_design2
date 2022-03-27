package ru.job4j.bank;

import java.util.*;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Добавляет нового пользователя в коллекцию пользователей и счетов.
     *
     * @param user добавляемый пользователь.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Добавляет новый счет пользователю
     *
     * @param passport id пользователя
     * @param account  acc для добавления
     */
    public void addAccount(String passport, Account account) {
        Optional<User> byPassport = findByPassport(passport);
        if (byPassport.isPresent()) {
            User user = byPassport.get();
            List<Account> list = users.get(user);
            if (!list.contains(account)) {
                list.add(account);
            }
        }
    }

    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream().filter(
                user -> user.getPassport().equals(passport)).findFirst();
    }

    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<Account> result = Optional.empty();
        if (findByPassport(passport).isPresent()) {
            User user = findByPassport(passport).get();
            List<Account> list = users.get(user);
            result = list.stream().filter(
                    account -> account.getRequisite().equals(requisite)).findFirst();
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String s, double amount) {
        boolean rsl = false;
        Optional<Account> accountSrc = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> accountDest = findByRequisite(destPassport, s);
        if (accountSrc.isPresent() && accountDest.isPresent()) {
            if (accountSrc.get().getBalance() >= amount) {
                accountSrc.get().setBalance(accountSrc.get().getBalance() - amount);
                accountDest.get().setBalance(accountDest.get().getBalance() + amount);
                rsl = true;
            }
        }
        return rsl;
    }
}