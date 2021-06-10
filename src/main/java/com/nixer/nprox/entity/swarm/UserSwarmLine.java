package com.nixer.nprox.entity.swarm;

import io.swagger.models.auth.In;

import java.math.BigDecimal;

public class UserSwarmLine {

    private String date;
    private BigDecimal user_bzz;
    private Integer  user_cash_out;
    private Integer user_nodes_num;
    private Integer pool_cashout;
    private BigDecimal pool_bzz;
    private Integer pool_nodes_num;

 public String getDate() {
  return date;
 }

 public void setDate(String date) {
  this.date = date;
 }

 public BigDecimal getUser_bzz() {
  return user_bzz;
 }

 public void setUser_bzz(BigDecimal user_bzz) {
  this.user_bzz = user_bzz;
 }

 public Integer getUser_cash_out() {
  return user_cash_out;
 }

 public void setUser_cash_out(Integer user_cash_out) {
  this.user_cash_out = user_cash_out;
 }

 public Integer getUser_nodes_num() {
  return user_nodes_num;
 }

 public void setUser_nodes_num(Integer user_nodes_num) {
  this.user_nodes_num = user_nodes_num;
 }

 public Integer getPool_cashout() {
  return pool_cashout;
 }

 public void setPool_cashout(Integer pool_cashout) {
  this.pool_cashout = pool_cashout;
 }

 public BigDecimal getPool_bzz() {
  return pool_bzz;
 }

 public void setPool_bzz(BigDecimal pool_bzz) {
  this.pool_bzz = pool_bzz;
 }

 public Integer getPool_nodes_num() {
  return pool_nodes_num;
 }

 public void setPool_nodes_num(Integer pool_nodes_num) {
  this.pool_nodes_num = pool_nodes_num;
 }
}
