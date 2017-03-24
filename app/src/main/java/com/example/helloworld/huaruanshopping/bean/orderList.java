package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/3/18.
 */

public class orderList {
    @SerializedName("orderList")
    private List<OrderListBean> orderList;

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * id : 1487779792136
         * sorderSet : [{"number":3,"price":63,"protype":{"name":"海鲜味","pic":"/img/2.jpg","product":{"id":1,"price":22}}},{"number":43,"price":989,"protype":{"name":"芥末味","pic":"/img/4.jpg","product":{"id":2,"price":23}}}]
         * status : {"status":"已下单"}
         */
        @SerializedName("id")
        private String id;
        @SerializedName("status")
        private StatusBean status;
        @SerializedName("sorderSet")
        private List<SorderSetBean> sorderSet;
        @SerializedName("total")
        private String total;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public StatusBean getStatus() {
            return status;
        }

        public void setStatus(StatusBean status) {
            this.status = status;
        }

        public List<SorderSetBean> getSorderSet() {
            return sorderSet;
        }

        public void setSorderSet(List<SorderSetBean> sorderSet) {
            this.sorderSet = sorderSet;
        }

        public static class StatusBean {
            /**
             * status : 已下单
             */
            @SerializedName("status")
            private String status;
            @SerializedName("id")
            private int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class SorderSetBean {
            /**
             * number : 3
             * price : 63.0
             * protype : {"name":"海鲜味","pic":"/img/2.jpg","product":{"id":1,"price":22}}
             */
            @SerializedName("number")
            private int number;
            @SerializedName("price")
            private double price;
            @SerializedName("protype")
            private ProtypeBean protype;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public ProtypeBean getProtype() {
                return protype;
            }

            public void setProtype(ProtypeBean protype) {
                this.protype = protype;
            }

            public static class ProtypeBean {
                /**
                 * name : 海鲜味
                 * pic : /img/2.jpg
                 * product : {"id":1,"price":22}
                 */
                @SerializedName("name")
                private String name;
                @SerializedName("pic")
                private String pic;
                @SerializedName("product")
                private ProductBean product;

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
                     * id : 1
                     * price : 22.0
                     */
                    @SerializedName("id")
                    private int id;
                    @SerializedName("price")
                    private double price;
                    @SerializedName("name")
                    private String name;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public double getPrice() {
                        return price;
                    }

                    public void setPrice(double price) {
                        this.price = price;
                    }
                }
            }
        }
    }
}
