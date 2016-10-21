package dev.mvc.tmember;

import java.util.HashMap;
import java.util.List;



public interface MemberDAOInter {
  /**
   * �ߺ� ���̵� �˻��մϴ�.
   * <select id='checkId' resultType='int' parameterType='String'>
   * @param id ���̵�
   * @return 0: �ߺ� �ƴ�, 1: �ߺ�
   */
  public int checkId(String id);
  
  /**
   * �ߺ� ���̵� �˻��մϴ�.
   * <select id='checkNickname' resultType='int' parameterType='String'>
   * @param checkNickname �г���
   * @return 0: �ߺ� �ƴ�, 1: �ߺ�
   */
  public int checkNickname(String nickname);
  
  /**
   * �ߺ� ���̵� �˻��մϴ�.
   * <select id='checkEmail' resultType='int' parameterType='String'>
   * @param email �̸���
   * @return 0: �ߺ� �ƴ�, 1: �ߺ�
   */
  public int checkEmail(String email);
 
  /**
   * ���ڵ带 ����մϴ�.
   * <insert id="create" parameterType="MemberVO">
   * @param vo ����� ������ ��ü
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(MemberVO vo);
   
  /**
   * ȸ�� ��ü ���
   * <select id="list" resultType="MemberVO">
   * @return ȸ�� ���
   */
  public List<MemberVO> list();
  
  /**
   * ȸ�� ���� ��ȸ
   * <select id="read" resultType="MemberVO" parameterType="int">
   * @param mno
   * @return
   */
  public MemberVO read(int mno); 
  
  /**
   * ȸ�� ���� ��ȸ
   * <select id="read" resultType="MemberVO" parameterType="String">
   * @param userid
   * @return
   */
  public MemberVO read_userid(String userid); 
  
  /**
   * ȸ���� �����մϴ�.
   * <update id="update" parameterType="MemberVO"> 
   * @param memberVO
   * @return ������ ���ڵ� ����
   */
  public int update(MemberVO memberVO);
  
  /** ���� ���Խ� ������ ��ȸ
   *  <select id="admin_search" resultType="int" parameterType="String">
   * @param act
   * @return
   */
  public int admin_search(String act);
  
  /** �̸��� ���� ó��
   * <update id="confirm" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int confirm(MemberVO memberVO);
  
  /** �α���
   * <select id="login" resultType="int" parameterType="MemberVO">
   * @param memberVO
   * @return
   */
  public int login(MemberVO memberVO);
  
  /**
   * Ż�� ��û
   * <update id="dropout" parameterType="MemberVO">
   * @param memberVO
   * @return 
   */
  public int dropout(MemberVO memberVO);
  
  /**
   * ���� ���� - ��й�ȣ Ȯ��
    <select id="checkPwd" resultType="int" parameterType="Map">
   * @param userid
   * @param pwd
   * @return
   */
  public int checkPwd(String userid, String pwd);

  /** ���� �� �г��� �ߺ� ȯ��
   * <select id='checkNickname_update' resultType='int' parameterType='map'>
   * @param userid �ڽ��� ����
   * @param pwd
   * @return
   */
  public int checkNickname_update(HashMap map);
  
  /** ���� �� �̸��� �ߺ� Ȯ��
   * <select id='checkEmail_update' resultType='int' parameterType='Map'>
   * @param userid
   * @param pwd
   * @return
   */
  public int checkEmail_update(HashMap map);
}