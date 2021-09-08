package org.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// �� �����̳ʿ� �־��ּ���.(��ϵ� ��Ʈ�ѷ��� ������ �۵���)
@Controller
public class MvcController {
	
	// value = �⺻�ּ�(localhost:8181) �ڿ� /goA�� ���̸� goA()�޼��� ����
	@RequestMapping(value="/goA")
	// return Ÿ���� String�� ��� ��� �������� ������ �� ����
	public String goA() {
		System.out.println("goA �ּ� ���� ����");
		// ��� �������� views ���� �Ʒ��� A.jsp
		return "A";
	}
	@RequestMapping(value="/goB")
	public String goB() {
		System.out.println("goB �ּ� ���� ����");
		return "B";
	}
	
	// goC�� �Ķ���͸� �Է¹��� �� �ֵ��� �غ��ڽ��ϴ�.
	@RequestMapping(value="/goC")
	// �ּ� �� ?cNum=�� ���·� ������ ���� ���� �� cNum���� ó���մϴ�.
	// ���� �Ķ���͸� .jsp���Ϸ� �����ϱ� ���ؼ���
	// Model model�� �Ķ���Ϳ� �߰��� �������ݴϴ�.
	public String goC(int cNum, Model model) {
		System.out.println("�ּҷ� ���޹��� �� : " + cNum);
		
		// ���޹��� cNum�� C.jsp�� ����ϴ� ������ �ۼ����ּ���.
		model.addAttribute("cNum", cNum);
		
		return "C";
	}
	
	// goD�� requestParam�� �̿��� ������� �޴� �̸��� ��ġ���� �ʰ� �غ��ڽ��ϴ�.
	@RequestMapping(value="/goD")
	// @RequestParam("��ü�̸�")�� ���� ���ʿ� �����մϴ�.
	// �̷��� �Ǹ� ���� ������ ��� ��ü�̸����� ġȯ�� �޾ƿɴϴ�.
	// ���� @RequestParam�� ���� ������ �ʴ´ٸ� ������ ���� �ʽ��ϴ�.
	public String goD(@RequestParam("d") int dNum, Model model) {
		System.out.println("d ���������� ������ dNum�� ���� : " + dNum);
		model.addAttribute("dNum", dNum);
		return "D";
	}
	
	// ������ �����ϴ� �޼��嵵 ����ڽ��ϴ�.
	// ���� ��������������� �ּҸ� �����ϰ� �ϱ� ���ؼ� ������ �ٹ�� ���� ���
	@RequestMapping(value="/goctof", method=RequestMethod.GET)
	public String cToFForm() {
		// ctoffrom�� �̿��� �����µ��� �Է��ϰ� �����ư�� ������
		// ������� ������ ������ �ۼ����ּ���.
		// input �±��� name �Ӽ��� cNum���� �ֽø� �ǰ�
		// action�� value�� ���� �ּҰ����� �Ѱ��ֽø� �˴ϴ�.
		return "ctofform";
	}
	
	// cToF �޼��带 ����ڽ��ϴ�.
	// ���� �µ��� �Է¹޾� ȭ�� �µ��� �ٲ㼭 ������ִ� ������ �ۼ����ּ���.
	// (ȭ�� - 32) / 1.8 = ����
	@RequestMapping(value="/goctof", method=RequestMethod.POST)
	public String cToF(@RequestParam double cNum, Model model) {
		double fNum = cNum * 1.8 + 32;
		System.out.println("���� ���� : " + cNum);
		System.out.println("�ٲ� ȭ�� : " + fNum);
		model.addAttribute("cNum", cNum);
		model.addAttribute("fNum", fNum);
		return "ctof";
	}
	
	// ���� ���� ������� bmi������������ �����ڽ��ϴ�.
	// ���� ����������� �������ֽø� �ǰ�
	// bmi ������ ü�� / (Ű(m) ^ 2) ���� ������ ����Դϴ�.
	@RequestMapping(value="/bmi", method=RequestMethod.GET)
	public String inputbmi() {
		return "inputbmi";
	}
	
	@RequestMapping(value="/bmi", method=RequestMethod.POST)
	public String bmi(double height, double weight, Model model) {
		double bmi = weight / ((height / 100) * (height / 100));
		bmi = (double)Math.round(bmi * 100) / 100;
		model.addAttribute("height", height);
		model.addAttribute("weight", weight);
		model.addAttribute("bmi", bmi);
		return "bmi";
	}
	
	// ParaVariable�� �̿��ϸ� url ���ϸ����ε� Ư�� �Ķ���͸� �޾ƿ� �� �ֽ��ϴ�.
	// rest������� url�� ó���� �� �ַ� ����ϴ� ����Դϴ�.
	// /pathtest/���� �� ���� ��ġ�� �� ���� page��� ���������� ����
	@RequestMapping(value="/pathtest/{page}")
	public String pathText(@PathVariable int page, Model model) {
		// �޾ƿ� page������ path.jsp�� �����ּ���
		// path.jsp���� {page} ������ ��ȸ���Դϴ� ��� ������ �߰� ���ּ���.
		model.addAttribute("page", page);
		return "path";
	}
	
	// ctof ������ PathVariable�� �����ؼ� ������ּ���.
	// ctofpv.jsp �� ����� ������ �˴ϴ�.
	// ���� �µ��� url�� �Ϻη� �޵��� ó��
	@RequestMapping(value="/ctofpv/{cNum}")
	public String cToFPV(@PathVariable double cNum, Model model) {
		double fNum = cNum * 1.8 + 32;
		System.out.println("���� ���� : " + cNum);
		System.out.println("�ٲ� ȭ�� : " + fNum);
		model.addAttribute("cNum", cNum);
		model.addAttribute("fNum", fNum);
		return "ctofpv";
	}
	
	// void Ÿ�� ��Ʈ�ѷ��� Ư¡
	// void Ÿ���� ���� ������ ����Ҽ� ���� ��ŭ
	// view ������ �̸��� �׳� �޼����̸�.jsp �� �ڵ� ���� �ع����ϴ�.
	@RequestMapping(value="/voidreturn")
	public void voidTest(int num, Model model) {
		System.out.println("void ��Ʈ�ѷ��� ���ϱ����� �ʿ�����ϴ�.");
		// 1. �Ķ���͸� �ƹ��ų� �޾ƿ��� ���Ƿ� �������ּ���.
		// 2. �� �޼����� �´� view������ �������ּ���.
		// 3. 1���� ���� �Ķ���͸� 2�� ��µǵ��� �������ּ���.
		model.addAttribute("num", num);
	}
}