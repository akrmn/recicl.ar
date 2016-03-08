package ar.recicl.reciclar.data;

import ar.recicl.reciclar.R;

public class ShoppingProduct {
    private static final ShoppingProduct[] sProducts = new ShoppingProduct[]{
            new ShoppingProduct(0, "Bolsa de compras ecológica", R.drawable.shopping_bag,
                    350, "¡Para dejar atrás el uso de bolsas plásticas contaminantes!"),
            new ShoppingProduct(1, "Kit de utensilios alimenticios biodegradable", R.drawable.shopping_biodegradable_packaging,
                    675, "No tires más plástico contaminante con nuestros productos biodegradables."),
            new ShoppingProduct(2, "Cuaderno ecológico de papel reciclado", R.drawable.shopping_notebook,
                    1250, "Evitá el uso de papel y salvá nuestros bosques :)"),
            new ShoppingProduct(3, "Libro de recetas veganas «easy vegan»", R.drawable.shopping_veganbook,
                    2150, "Aprendé sencillas recetas veganas. ¡Cualquiera puede ser vegano!"),
            new ShoppingProduct(4, "Recipiente de composta para jardín", R.drawable.shopping_compost_bin,
                    3500, "¡Comenzá ya mismo a producir tu propia composta!"),
            new ShoppingProduct(5, "Camiseta con temática «Salva tu planeta»", R.drawable.shopping_tshirt,
                    4900, "¡Para promover la consciencia ecológica con tus amigos!"),
            new ShoppingProduct(6, "Medidor de tiempo para la ducha", R.drawable.shopping_shower_timer,
                    8750, "Ahorrá agua al momento de ducharte con este producto."),
            new ShoppingProduct(7, "Cargador solar para teléfonos móviles", R.drawable.shopping_solar_charger,
                    14600, "¡Ahorrá energía eléctrica cargando tu teléfono con energía solar!"),
            new ShoppingProduct(8, "Bicicleta verde ecofriendly", R.drawable.shopping_bike,
                    25000, "No contribuyás más con la contaminación de la atmósfera, utiliza tu" +
                    "bicicleta verde!")
    };
    public static ShoppingProduct[] getShoppingProductsList() {
        return sProducts;
    }
    private String mName;
    private int mPictureRes;
    private int mId;
    private int mPrice;
    private String mDescription;

    public ShoppingProduct(int id, String name, int pictureRes, int price, String description) {
        mName = name;
        mPictureRes = pictureRes;
        mId = id;
        mPrice = price;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public int getPictureRes() {
        return mPictureRes;
    }

    public int getId() {
        return mId;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getDescription() {
        return mDescription;
    }
}
