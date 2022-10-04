package co.edu.unisabana.parcialarquitectura.repository;

interface IDatabase {
    int savePurchase(int buyerCode, String item);
}
