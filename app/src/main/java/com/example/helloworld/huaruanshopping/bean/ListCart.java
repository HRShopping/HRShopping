package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/3/13.
 */

public class ListCart {


    /**
     * status : success
     * cart : [{"id":2,"number":8,"protype":{"id":1,"inventory":0,"name":"香辣","pic":"/img/1.jpg","product":{"commend":true,"create_date":"2017-03-12T15:56:52","id":1,"name":"康师傅方便面","open":true,"price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}}},{"id":6,"number":3,"protype":{"id":2,"inventory":0,"name":"海鲜味","pic":"/img/2.jpg","product":{"commend":true,"create_date":"2017-03-12T15:56:52","id":1,"name":"康师傅方便面","open":true,"price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}}}]
     */
    @SerializedName("status")
    private String status;
    @SerializedName("cart")
    private List<CartBean> cart;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean {
        /**
         * id : 2
         * number : 8
         * protype : {"id":1,"inventory":0,"name":"香辣","pic":"/img/1.jpg","product":{"commend":true,"create_date":"2017-03-12T15:56:52","id":1,"name":"康师傅方便面","open":true,"price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}}
         */
        @SerializedName("id")
        private int id;
        @SerializedName("number")
        private int number;
        @SerializedName("protype")
        private ProtypeBean protype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public ProtypeBean getProtype() {
            return protype;
        }

        public void setProtype(ProtypeBean protype) {
            this.protype = protype;
        }

        public static class ProtypeBean {
            /**
             * id : 1
             * inventory : 0
             * name : 香辣
             * pic : /img/1.jpg
             * product : {"commend":true,"create_date":"2017-03-12T15:56:52","id":1,"name":"康师傅方便面","open":true,"price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}
             */
            @SerializedName("id")
            private int id;
            @SerializedName("inventory")
            private int inventory;
            @SerializedName("name")
            private String name;
            @SerializedName("pic")
            private String pic;
            @SerializedName("product")
            private ProductBean product;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getInventory() {
                return inventory;
            }

            public void setInventory(int inventory) {
                this.inventory = inventory;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public ProductBean getProduct() {
                return product;
            }

            public void setProduct(ProductBean product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
                 * commend : true
                 * create_date : 2017-03-12T15:56:52
                 * id : 1
                 * name : 康师傅方便面
                 * open : true
                 * price : 22.0
                 * remark : 康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面
                 * sales : 0
                 */
                @SerializedName("true")
                private boolean commend;
                @SerializedName("create_date")
                private String create_date;
                @SerializedName("id")
                private int id;
                @SerializedName("name")
                private String name;
                @SerializedName("open")
                private boolean open;
                @SerializedName("price")
                private double price;
                @SerializedName("remark")
                private String remark;
                @SerializedName("sales")
                private int sales;

                public boolean isCommend() {
                    return commend;
                }

                public void setCommend(boolean commend) {
                    this.commend = commend;
                }

                public String getCreate_date() {
                    return create_date;
                }

                public void setCreate_date(String create_date) {
                    this.create_date = create_date;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isOpen() {
                    return open;
                }

                public void setOpen(boolean open) {
                    this.open = open;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public int getSales() {
                    return sales;
                }

                public void setSales(int sales) {
                    this.sales = sales;
                }
            }
        }
    }
}
